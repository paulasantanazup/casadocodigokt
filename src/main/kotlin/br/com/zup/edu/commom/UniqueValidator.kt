package br.com.zup.edu.commom

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

open class UniqueValidator : ConstraintValidator<Unique, Object> {

    @PersistenceContext
    lateinit var manager: EntityManager

    private lateinit var domainAttribute: String
    private lateinit var domainClass: KClass<*>

    override fun initialize(params: Unique) {
        domainAttribute = params.domainAttribute
        domainClass = params.domainClass
    }

    override fun isValid(value: Object?, context: ConstraintValidatorContext?): Boolean {
        val query: Query =
            manager.createQuery(
                "select 1 from " + domainClass!!.simpleName + " where " + domainAttribute + "=:value")
        query.setParameter("value", value)
        val list: List<*> = query.getResultList()

        return list.isEmpty()
    }
}
