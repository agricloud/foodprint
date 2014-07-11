databaseChangeLog = {

	changeSet(author: "pipi (generated)", id: "1405098814504-1") {
		modifyDataType(columnName: "expect_qty", newDataType: "double precision", tableName: "batch")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-2") {
		addNotNullConstraint(columnDataType: "double precision", columnName: "expect_qty", tableName: "batch")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-4") {
		dropIndex(indexName: "unique_report_params_id", tableName: "batch_report_det")
	}


	changeSet(author: "pipi (generated)", id: "1405098814504-12") {
		dropIndex(indexName: "unique_param_id", tableName: "report_params")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-14") {
		dropIndex(indexName: "username_uniq_1389858126019", tableName: "user")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-17") {
		createIndex(indexName: "unique_report_params_id", tableName: "batch_report_det", unique: "true") {
			column(name: "site_id")

			column(name: "batch_id")

			column(name: "report_params_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-26") {
		createIndex(indexName: "unique_param_id", tableName: "report_params", unique: "true") {
			column(name: "site_id")

			column(name: "report_id")

			column(name: "param_id")
		}
	}


	changeSet(author: "pipi (generated)", id: "1405098814504-28") {
		createIndex(indexName: "unique_username", tableName: "user", unique: "true") {
			column(name: "site_id")

			column(name: "username")
		}
	}

}
