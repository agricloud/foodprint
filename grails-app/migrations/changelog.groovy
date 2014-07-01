databaseChangeLog = {

	changeSet(author: "pipi (generated)", id: "1389858126106-1") {
		createTable(tableName: "batch") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "batchPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "batch_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "country", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "due_date", type: "datetime")

			column(name: "editor", type: "varchar(255)")

			column(name: "expect_qty", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "expiration_date", type: "datetime")

			column(name: "item_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "manufacture_date", type: "datetime")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "remark", type: "varchar(255)")

			column(name: "site_id", type: "bigint")

			column(name: "supplier_id", type: "bigint")

			column(name: "uuid", type: "varchar(255)")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-2") {
		createTable(tableName: "batch_report_det") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "batch_report_PK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "batch_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "batch_route_id", type: "bigint")

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "report_params_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "value", type: "varchar(255)")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-3") {
		createTable(tableName: "batch_route") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "batch_routePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "batch_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)")

			column(name: "end_date", type: "datetime")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "operation_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sequence", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "start_date", type: "datetime")

			column(name: "supplier_id", type: "bigint")

			column(name: "workstation_id", type: "bigint")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-4") {
		createTable(tableName: "batch_source") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "batch_sourcePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "batch_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "child_batch_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-5") {
		createTable(tableName: "customer") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "customerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "address", type: "varchar(255)")

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)")

			column(name: "email", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "title", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-6") {
		createTable(tableName: "encrypted_data") {
			column(name: "id", type: "varchar(32)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "encrypted_datPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "data_item", type: "varchar(512)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-7") {
		createTable(tableName: "item") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "itemPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "due_days", type: "bigint")

			column(name: "editor", type: "varchar(255)")

			column(name: "effect_end_date", type: "datetime")

			column(name: "effect_start_date", type: "datetime")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "spec", type: "varchar(255)")

			column(name: "title", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "unit", type: "varchar(255)")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-8") {
		createTable(tableName: "item_route") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "item_routePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)")

			column(name: "item_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "operation_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sequence", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "supplier_id", type: "bigint")

			column(name: "workstation_id", type: "bigint")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-9") {
		createTable(tableName: "operation") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "operationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "editor", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "title", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-10") {
		createTable(tableName: "param") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "paramPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "default_value", type: "varchar(255)")

			column(name: "description", type: "varchar(255)")

			column(name: "editor", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "lower", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "param_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "title", type: "varchar(255)")

			column(name: "unit", type: "varchar(255)")

			column(name: "upper", type: "varchar(255)")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-11") {
		createTable(tableName: "report") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "reportPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "editor", type: "varchar(255)")

			column(name: "effect_end_date", type: "datetime")

			column(name: "effect_start_date", type: "datetime")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "report_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "title", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-12") {
		createTable(tableName: "report_params") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "report_paramsPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "batch_id", type: "bigint")

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)")

			column(name: "item_id", type: "bigint")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "operation_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "param_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "report_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "supplier_id", type: "bigint")

			column(name: "workstation_id", type: "bigint")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-13") {
		createTable(tableName: "request_map") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "request_mapPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "config_attribute", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "url", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-14") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-15") {
		createTable(tableName: "site") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sitePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "address", type: "varchar(255)")

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "editor", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "title", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-16") {
		createTable(tableName: "supplier") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "supplierPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "address", type: "varchar(255)")

			column(name: "country", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)")

			column(name: "email", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "tel", type: "varchar(255)")

			column(name: "title", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-17") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)")

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "full_name", type: "varchar(255)")

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-18") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-19") {
		createTable(tableName: "workstation") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "workstationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)")

			column(name: "editor", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "title", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-20") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-59") {
		createIndex(indexName: "FK592D73A365BF459", tableName: "batch") {
			column(name: "supplier_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-60") {
		createIndex(indexName: "FK592D73A6B3BA5F9", tableName: "batch") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-61") {
		createIndex(indexName: "FK592D73A6C2E8279", tableName: "batch") {
			column(name: "item_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-62") {
		createIndex(indexName: "name_uniq_1389858125971", tableName: "batch", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-63") {
		createIndex(indexName: "FKCF51E4AD606AE3A2", tableName: "batch_report_det") {
			column(name: "report_params_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-64") {
		createIndex(indexName: "FKCF51E4AD6B3BA5F9", tableName: "batch_report_det") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-65") {
		createIndex(indexName: "FKCF51E4AD6E07D5BB", tableName: "batch_report_det") {
			column(name: "batch_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-66") {
		createIndex(indexName: "FKCF51E4ADD78243C4", tableName: "batch_report_det") {
			column(name: "batch_route_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-67") {
		createIndex(indexName: "unique_report_params_id", tableName: "batch_report_det", unique: "true") {
			column(name: "batch_id")

			column(name: "report_params_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-68") {
		createIndex(indexName: "FK1096190416A6A05B", tableName: "batch_route") {
			column(name: "workstation_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-69") {
		createIndex(indexName: "FK10961904365BF459", tableName: "batch_route") {
			column(name: "supplier_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-70") {
		createIndex(indexName: "FK109619046B3BA5F9", tableName: "batch_route") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-71") {
		createIndex(indexName: "FK109619046E07D5BB", tableName: "batch_route") {
			column(name: "batch_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-72") {
		createIndex(indexName: "FK1096190471A4821B", tableName: "batch_route") {
			column(name: "operation_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-73") {
		createIndex(indexName: "unique_sequence", tableName: "batch_route", unique: "true") {
			column(name: "batch_id")

			column(name: "sequence")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-74") {
		createIndex(indexName: "FK3E1D8C01675117E", tableName: "batch_source") {
			column(name: "child_batch_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-75") {
		createIndex(indexName: "FK3E1D8C06B3BA5F9", tableName: "batch_source") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-76") {
		createIndex(indexName: "FK3E1D8C06E07D5BB", tableName: "batch_source") {
			column(name: "batch_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-77") {
		createIndex(indexName: "unique_child_batch_id", tableName: "batch_source", unique: "true") {
			column(name: "batch_id")

			column(name: "child_batch_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-78") {
		createIndex(indexName: "FK24217FDE6B3BA5F9", tableName: "customer") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-79") {
		createIndex(indexName: "name_uniq_1389858125998", tableName: "customer", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-80") {
		createIndex(indexName: "FK317B136B3BA5F9", tableName: "item") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-81") {
		createIndex(indexName: "name_uniq_1389858126001", tableName: "item", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-82") {
		createIndex(indexName: "FK8B96B61D16A6A05B", tableName: "item_route") {
			column(name: "workstation_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-83") {
		createIndex(indexName: "FK8B96B61D365BF459", tableName: "item_route") {
			column(name: "supplier_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-84") {
		createIndex(indexName: "FK8B96B61D6B3BA5F9", tableName: "item_route") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-85") {
		createIndex(indexName: "FK8B96B61D6C2E8279", tableName: "item_route") {
			column(name: "item_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-86") {
		createIndex(indexName: "FK8B96B61D71A4821B", tableName: "item_route") {
			column(name: "operation_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-87") {
		createIndex(indexName: "unique_sequence", tableName: "item_route", unique: "true") {
			column(name: "item_id")

			column(name: "sequence")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-88") {
		createIndex(indexName: "FK631AD5676B3BA5F9", tableName: "operation") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-89") {
		createIndex(indexName: "name_uniq_1389858126005", tableName: "operation", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-90") {
		createIndex(indexName: "FK658188D6B3BA5F9", tableName: "param") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-91") {
		createIndex(indexName: "name_uniq_1389858126006", tableName: "param", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-92") {
		createIndex(indexName: "FKC84C55346B3BA5F9", tableName: "report") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-93") {
		createIndex(indexName: "FKCDF21EF116A6A05B", tableName: "report_params") {
			column(name: "workstation_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-94") {
		createIndex(indexName: "FKCDF21EF11999F0DB", tableName: "report_params") {
			column(name: "param_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-95") {
		createIndex(indexName: "FKCDF21EF1365BF459", tableName: "report_params") {
			column(name: "supplier_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-96") {
		createIndex(indexName: "FKCDF21EF16B3BA5F9", tableName: "report_params") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-97") {
		createIndex(indexName: "FKCDF21EF16C2E8279", tableName: "report_params") {
			column(name: "item_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-98") {
		createIndex(indexName: "FKCDF21EF16E07D5BB", tableName: "report_params") {
			column(name: "batch_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-99") {
		createIndex(indexName: "FKCDF21EF171A4821B", tableName: "report_params") {
			column(name: "operation_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-100") {
		createIndex(indexName: "FKCDF21EF193BA5719", tableName: "report_params") {
			column(name: "report_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-101") {
		createIndex(indexName: "unique_param_id", tableName: "report_params", unique: "true") {
			column(name: "report_id")

			column(name: "param_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-102") {
		createIndex(indexName: "authority_uniq_1389858126012", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-103") {
		createIndex(indexName: "name_uniq_1389858126013", tableName: "site", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-104") {
		createIndex(indexName: "FK9CDBF9CC6B3BA5F9", tableName: "supplier") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-105") {
		createIndex(indexName: "name_uniq_1389858126017", tableName: "supplier", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-106") {
		createIndex(indexName: "FK36EBCB6B3BA5F9", tableName: "user") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-107") {
		createIndex(indexName: "username_uniq_1389858126019", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-108") {
		createIndex(indexName: "FK143BF46A40216399", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-109") {
		createIndex(indexName: "FK143BF46AE54C2779", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-110") {
		createIndex(indexName: "FK22AA55036B3BA5F9", tableName: "workstation") {
			column(name: "site_id")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-111") {
		createIndex(indexName: "name_uniq_1389858126020", tableName: "workstation", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-21") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "batch", constraintName: "FK592D73A6C2E8279", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-22") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "batch", constraintName: "FK592D73A6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-23") {
		addForeignKeyConstraint(baseColumnNames: "supplier_id", baseTableName: "batch", constraintName: "FK592D73A365BF459", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "supplier", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-24") {
		addForeignKeyConstraint(baseColumnNames: "batch_id", baseTableName: "batch_report_det", constraintName: "FKCF51E4AD6E07D5BB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "batch", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-25") {
		addForeignKeyConstraint(baseColumnNames: "batch_route_id", baseTableName: "batch_report_det", constraintName: "FKCF51E4ADD78243C4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "batch_route", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-26") {
		addForeignKeyConstraint(baseColumnNames: "report_params_id", baseTableName: "batch_report_det", constraintName: "FKCF51E4AD606AE3A2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "report_params", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-27") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "batch_report_det", constraintName: "FKCF51E4AD6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-28") {
		addForeignKeyConstraint(baseColumnNames: "batch_id", baseTableName: "batch_route", constraintName: "FK109619046E07D5BB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "batch", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-29") {
		addForeignKeyConstraint(baseColumnNames: "operation_id", baseTableName: "batch_route", constraintName: "FK1096190471A4821B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "operation", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-30") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "batch_route", constraintName: "FK109619046B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-31") {
		addForeignKeyConstraint(baseColumnNames: "supplier_id", baseTableName: "batch_route", constraintName: "FK10961904365BF459", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "supplier", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-32") {
		addForeignKeyConstraint(baseColumnNames: "workstation_id", baseTableName: "batch_route", constraintName: "FK1096190416A6A05B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "workstation", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-33") {
		addForeignKeyConstraint(baseColumnNames: "batch_id", baseTableName: "batch_source", constraintName: "FK3E1D8C06E07D5BB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "batch", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-34") {
		addForeignKeyConstraint(baseColumnNames: "child_batch_id", baseTableName: "batch_source", constraintName: "FK3E1D8C01675117E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "batch", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-35") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "batch_source", constraintName: "FK3E1D8C06B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-36") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "customer", constraintName: "FK24217FDE6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-37") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "item", constraintName: "FK317B136B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-38") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "item_route", constraintName: "FK8B96B61D6C2E8279", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-39") {
		addForeignKeyConstraint(baseColumnNames: "operation_id", baseTableName: "item_route", constraintName: "FK8B96B61D71A4821B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "operation", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-40") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "item_route", constraintName: "FK8B96B61D6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-41") {
		addForeignKeyConstraint(baseColumnNames: "supplier_id", baseTableName: "item_route", constraintName: "FK8B96B61D365BF459", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "supplier", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-42") {
		addForeignKeyConstraint(baseColumnNames: "workstation_id", baseTableName: "item_route", constraintName: "FK8B96B61D16A6A05B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "workstation", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-43") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "operation", constraintName: "FK631AD5676B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-44") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "param", constraintName: "FK658188D6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-45") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "report", constraintName: "FKC84C55346B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-46") {
		addForeignKeyConstraint(baseColumnNames: "batch_id", baseTableName: "report_params", constraintName: "FKCDF21EF16E07D5BB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "batch", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-47") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "report_params", constraintName: "FKCDF21EF16C2E8279", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-48") {
		addForeignKeyConstraint(baseColumnNames: "operation_id", baseTableName: "report_params", constraintName: "FKCDF21EF171A4821B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "operation", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-49") {
		addForeignKeyConstraint(baseColumnNames: "param_id", baseTableName: "report_params", constraintName: "FKCDF21EF11999F0DB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "param", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-50") {
		addForeignKeyConstraint(baseColumnNames: "report_id", baseTableName: "report_params", constraintName: "FKCDF21EF193BA5719", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "report", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-51") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "report_params", constraintName: "FKCDF21EF16B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-52") {
		addForeignKeyConstraint(baseColumnNames: "supplier_id", baseTableName: "report_params", constraintName: "FKCDF21EF1365BF459", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "supplier", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-53") {
		addForeignKeyConstraint(baseColumnNames: "workstation_id", baseTableName: "report_params", constraintName: "FKCDF21EF116A6A05B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "workstation", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-54") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "supplier", constraintName: "FK9CDBF9CC6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-55") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "user", constraintName: "FK36EBCB6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-56") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46A40216399", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-57") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46AE54C2779", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "pipi (generated)", id: "1389858126106-58") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "workstation", constraintName: "FK22AA55036B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	include file: '1.0.1.groovy'
}
