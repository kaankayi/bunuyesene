package knb.bunuyesene

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform