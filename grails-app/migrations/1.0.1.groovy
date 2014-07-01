databaseChangeLog = {

	changeSet(author: "pipi (generated)", id: "1404154765093-1") {
		addColumn(tableName: "customer") {
			column(name: "contact", type: "varchar(255)")
		}
	}

	changeSet(author: "pipi (generated)", id: "1404154765093-2") {
		addColumn(tableName: "customer") {
			column(name: "fax", type: "varchar(255)")
		}
	}

	changeSet(author: "pipi (generated)", id: "1404154765093-3") {
		addColumn(tableName: "customer") {
			column(name: "shipping_address", type: "varchar(255)")
		}
	}

	changeSet(author: "pipi (generated)", id: "1404154765093-4") {
		addColumn(tableName: "customer") {
			column(name: "tel", type: "varchar(255)")
		}
	}

	changeSet(author: "pipi (generated)", id: "1404154765093-5") {
		addColumn(tableName: "supplier") {
			column(name: "contact", type: "varchar(255)")
		}
	}

	changeSet(author: "pipi (generated)", id: "1404154765093-6") {
		addColumn(tableName: "supplier") {
			column(name: "fax", type: "varchar(255)")
		}
	}

	changeSet(author: "pipi (generated)", id: "1404154765093-7") {
		addNotNullConstraint(columnDataType: "varchar(255)", columnName: "unit", tableName: "item")
	}

	changeSet(author: "pipi (generated)", id: "1404154765093-8") {
		addNotNullConstraint(columnDataType: "bigint", columnName: "item_id", tableName: "report_params")
	}

	changeSet(author: "pipi (generated)", id: "1404154765093-9") {
		dropForeignKeyConstraint(baseTableName: "report_params", baseTableSchemaName: "foodprint", constraintName: "FKCDF21EF16E07D5BB")
	}

	changeSet(author: "pipi (generated)", id: "1404154765093-10") {
		dropColumn(columnName: "batch_id", tableName: "report_params")
	}
}
