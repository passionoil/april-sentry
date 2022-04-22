package io.april.sentry

@JsModule("@sentry/browser")
@JsNonModule
external object Sentry {

    interface Options {
        var environment: String?
        var dsn: String?
        var integrations: Array<dynamic>?
        var tracesSampleRate: Number?
    }

    fun init(options: Options)

}

@JsModule("@sentry/tracing")
@JsNonModule
external object SentryTracing {

    class BrowserTracing

}
