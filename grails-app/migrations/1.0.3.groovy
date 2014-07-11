databaseChangeLog = {

	changeSet(author: "pipi (generated)", id: "1405098814504-1") {
		modifyDataType(columnName: "expect_qty", newDataType: "double precision", tableName: "batch")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-2") {
		addNotNullConstraint(columnDataType: "double precision", columnName: "expect_qty", tableName: "batch")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-3") {
		dropIndex(indexName: "name_uniq_1389858125971", tableName: "batch")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-4") {
		dropIndex(indexName: "unique_report_params_id", tableName: "batch_report_det")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-5") {
		dropIndex(indexName: "unique_sequence", tableName: "batch_route")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-6") {
		dropIndex(indexName: "unique_child_batch_id", tableName: "batch_source")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-7") {
		dropIndex(indexName: "name_uniq_1389858125998", tableName: "customer")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-8") {
		dropIndex(indexName: "name_uniq_1389858126001", tableName: "item")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-9") {
		dropIndex(indexName: "unique_sequence", tableName: "item_route")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-10") {
		dropIndex(indexName: "name_uniq_1389858126005", tableName: "operation")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-11") {
		dropIndex(indexName: "name_uniq_1389858126006", tableName: "param")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-12") {
		dropIndex(indexName: "unique_param_id", tableName: "report_params")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-13") {
		dropIndex(indexName: "name_uniq_1389858126017", tableName: "supplier")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-14") {
		dropIndex(indexName: "username_uniq_1389858126019", tableName: "user")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-15") {
		dropIndex(indexName: "name_uniq_1389858126020", tableName: "workstation")
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-16") {
		createIndex(indexName: "unique_name", tableName: "batch", unique: "true") {
			column(name: "site_id")

			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-17") {
		createIndex(indexName: "unique_report_params_id", tableName: "batch_report_det", unique: "true") {
			column(name: "site_id")

			column(name: "batch_id")

			column(name: "report_params_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-18") {
		createIndex(indexName: "unique_sequence", tableName: "batch_route", unique: "true") {
			column(name: "site_id")

			column(name: "batch_id")

			column(name: "sequence")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-19") {
		createIndex(indexName: "unique_child_batch_id", tableName: "batch_source", unique: "true") {
			column(name: "site_id")

			column(name: "batch_id")

			column(name: "child_batch_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-20") {
		createIndex(indexName: "unique_name", tableName: "customer", unique: "true") {
			column(name: "site_id")

			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-21") {
		createIndex(indexName: "unique_name", tableName: "item", unique: "true") {
			column(name: "site_id")

			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-22") {
		createIndex(indexName: "unique_sequence", tableName: "item_route", unique: "true") {
			column(name: "site_id")

			column(name: "item_id")

			column(name: "sequence")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-23") {
		createIndex(indexName: "unique_name", tableName: "operation", unique: "true") {
			column(name: "site_id")

			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-24") {
		createIndex(indexName: "unique_name", tableName: "param", unique: "true") {
			column(name: "site_id")

			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-25") {
		createIndex(indexName: "unique_name", tableName: "report", unique: "true") {
			column(name: "site_id")

			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-26") {
		createIndex(indexName: "unique_param_id", tableName: "report_params", unique: "true") {
			column(name: "site_id")

			column(name: "report_id")

			column(name: "param_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-27") {
		createIndex(indexName: "unique_name", tableName: "supplier", unique: "true") {
			column(name: "site_id")

			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-28") {
		createIndex(indexName: "unique_username", tableName: "user", unique: "true") {
			column(name: "site_id")

			column(name: "username")
		}
	}

	changeSet(author: "pipi (generated)", id: "1405098814504-29") {
		createIndex(indexName: "unique_name", tableName: "workstation", unique: "true") {
			column(name: "site_id")

			column(name: "name")
		}
	}
}
