databaseChangeLog = {

	changeSet(author: "pipi (generated)", id: "1405370838862-1") {
		dropIndex(indexName: "unique_name", tableName: "batch")
	}

	changeSet(author: "pipi (generated)", id: "1405370838862-2") {
		createIndex(indexName: "name_uniq_1405370838367", tableName: "batch", unique: "true") {
			column(name: "name")
		}
	}
}
