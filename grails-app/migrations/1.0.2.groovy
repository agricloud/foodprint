databaseChangeLog = {

	changeSet(author: "pipi (generated)", id: "1404898562028-1") {
		modifyDataType(columnName: "expect_qty", newDataType: "double precision", tableName: "batch")
	}
}
