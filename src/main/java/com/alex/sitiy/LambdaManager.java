package com.alex.sitiy;

import com.alex.sitiy.event.LambdaEvent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


@Named(value = "main")
@SuppressWarnings("rawtypes")
public class LambdaManager implements RequestStreamHandler {

    private final Instance<LambdaProvider> lambdaProviders;
    private final ObjectMapper objectMapper;

    @Inject
    public LambdaManager(Instance<LambdaProvider> lambdaProviders, ObjectMapper objectMapper) {
        this.lambdaProviders = lambdaProviders;
        this.objectMapper = objectMapper;
    }

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context)
            throws IOException {
        LambdaEvent lambdaEvent = null;
        try (input) {
            lambdaEvent = objectMapper.readValue(input.readAllBytes(), LambdaEvent.class);
        } catch (Exception exception) {
            Log.errorf("Unsupported event type. {}", exception.getMessage());
            throw new RuntimeException(exception);
        }

        for (LambdaProvider provider : lambdaProviders) {
            if (provider.support(lambdaEvent)) {
                Object response = provider.handleRequest(lambdaEvent, context);
                String result = objectMapper.writeValueAsString(response);
                Log.infof("Result -> {}", result);
                output.write(result.getBytes(StandardCharsets.UTF_8));
                output.close();
                return;
            }
        }
        throw new RuntimeException("There is no Provider that can handle your request");
    }
}
