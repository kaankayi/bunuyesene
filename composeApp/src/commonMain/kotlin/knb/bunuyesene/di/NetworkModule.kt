package knb.bunuyesene.di

import io.ktor.client.HttpClient
import org.koin.dsl.module
import knb.bunuyesene.features.common.data.api.httpClient



fun networkModule()  = module {

    single<HttpClient> {
        httpClient
    }
}

