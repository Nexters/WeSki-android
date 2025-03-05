package com.dieski.data

/**
 * @author   JGeun
 * @created  2025/03/05
 */
internal interface DataMapper<DomainModel> {
	fun toDomain(): DomainModel
}

internal fun <EntityModel, DomainModel> EntityModel.toDomainModel(): DomainModel {
	@Suppress("UNCHECKED_CAST")
	return when (this) {
		is DataMapper<*> -> toDomain()
		is List<*> -> map {
			val domainModel: DomainModel = it.toDomainModel()
			domainModel
		}
		is Unit -> this
		is Boolean -> this
		is Int -> this
		is String -> this
		is Byte -> this
		is Short -> this
		is Long -> this
		is Char -> this
		else -> {
			throw IllegalArgumentException("DataModel은 DataMapper<>, List<DataMapper<>>, Unit중 하나여야 함")
		}
	} as DomainModel
}

internal fun <EntityModel : DataMapper<DomainModel>, DomainModel> List<EntityModel>.toDomain(): List<DomainModel> {
	return map(DataMapper<DomainModel>::toDomain)
}
