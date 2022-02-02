package br.com.zup.edu.livro

import br.com.zup.edu.author.Author
import br.com.zup.edu.categoria.Category
import java.math.BigDecimal
import java.time.LocalTime
import javax.persistence.*

@Entity
class Book(
    val title:String,
    val abstract:String,
    val sumary:String,
    val price:BigDecimal,
    val pages:Int,
    val isbn:String,
    val publicationDate:LocalTime,
    @ManyToOne
    val category: Category,
    @ManyToOne
    val author:Author) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    val createAt: LocalTime = LocalTime.now()
}