package ru.example.service;

import com.google.auto.service.AutoService;
import io.opentelemetry.javaagent.bootstrap.http.HttpServerResponseCustomizer;
import io.opentelemetry.javaagent.bootstrap.http.HttpServerResponseMutator;
import io.opentelemetry.javaagent.shaded.io.opentelemetry.api.trace.Span;
import io.opentelemetry.javaagent.shaded.io.opentelemetry.api.trace.SpanContext;
import io.opentelemetry.javaagent.shaded.io.opentelemetry.context.Context;

@AutoService(HttpServerResponseCustomizer.class)
public class T2AgentHttpResponse implements HttpServerResponseCustomizer {

    private static String TRACE_ID = "trace-id";

        @Override
        public <RESPONSE> void customize(Context context, RESPONSE response, HttpServerResponseMutator<RESPONSE> responseMutator) {
            SpanContext spanContext = Span.fromContext(context).getSpanContext();
            String traceId = spanContext.getTraceId();
            responseMutator.appendHeader(response, TRACE_ID, traceId);
        }
}
