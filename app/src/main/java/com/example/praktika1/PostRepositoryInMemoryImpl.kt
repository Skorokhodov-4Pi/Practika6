package com.example.praktika1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
class PostRepositoryInMemoryImpl : PostRepository{
    private var posts = listOf(
        Post(
        id = 2,
        author = "Борисоглебский техникум промышленных и информационных техологий",
        content = "ГБПОУ ВО «БТПИТ» образовано в соответствии с постановлением правительства Воронежской области от 20 мая 2015 года № 401 в результате реорганизации в форме слияния ГОБУ СПО ВО «БИТ», ГОБУ СПО ВО «БТИВТ» и ГОБУ НПО ВО «ПУ № 34 г. Борисоглебска»\nОбразовательно-производственный центр (кластер) федерального проекта\n\"Профессионалитет\" по отраслям «Туризм и сфера услуг» на базе ГБПОУ ВО \"ХШН\" и «Педагогика» на базе ГБПОУ ВО \"ГПК\" .\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \"ХШН\"; сетевые ОО - ГБПОУ ВО \"БАИК\", ГБПОУ ВО \"ВГПГК\", ГБПОУ ВО \"ВТППП\", ГБПОУ ВО \"ВГПТК\", ГБПОУ ВО \"БТПИТ\".\nКолледжи-партнеры: Базовая ОО - ГБПОУ ВО \"ГПК\"; сетевые ОО - ГБПОУ ВО \"ВГПГК имени В.М. Пескова“, ГБПОУ ВО \"БТПИТ\".\nПодробнее о федеральном проекте «Профессионалитет» на сайте",
        published = "17 марта в 14:26",
        likes = 999999,
        share = 995,
        likedByMe = false,
        shareByMe = false
        ),
        Post(
            id = 1,
            author = "Борисоглебский техникум промышленных и информационных техологий",
            content = "В Борисоглебском техникуме промышленных и информационных технологий содержательно прошёл Всероссийский урок памяти «Возвращение в родную гавань». Урок наглядно показал, что история полуострова тесно связана с историей России.",
            published = "19 марта в 12:12",
            likes = 999997,
            share = 990,
            likedByMe = false,
            shareByMe = false
        ),
        Post(
            id = 3,
            author = "Борисоглебский техникум промышленных и информационных техологий",
            content = "В Борисоглебском техникуме промышленных и информационных технологий содержательно прошёл Всероссийский урок памяти «Возвращение в родную гавань». Урок наглядно показал, что история полуострова тесно связана с историей России.",
            published = "19 марта в 12:12",
            likes = 999997,
            share = 990,
            likedByMe = false,
            shareByMe = false
        ),
        Post(
            id = 4,
            author = "Борисоглебский техникум промышленных и информационных техологий",
            content = "В Борисоглебском техникуме промышленных и информационных технологий содержательно прошёл Всероссийский урок памяти «Возвращение в родную гавань». Урок наглядно показал, что история полуострова тесно связана с историей России.",
            published = "19 марта в 12:12",
            likes = 999997,
            share = 990,
            likedByMe = false,
            shareByMe = false
        )
    )
    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(likedByMe = !it.likedByMe)
        }
        posts.map {
            if (it.likedByMe) it.likes++ else it.likes--
        }


        data.value = posts
    }
    override fun shareByid(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(shareByMe = !it.shareByMe)
        }
        posts.map {
            if (it.id != id) it else it.share++
        }
        data.value = posts
    }
}

