databaseChangeLog = {

	changeSet(author: "Spooky (generated)", id: "1385045672493-1") {
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

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "due_date", type: "datetime")

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

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

	changeSet(author: "Spooky (generated)", id: "1385045672493-2") {
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

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

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

	changeSet(author: "Spooky (generated)", id: "1385045672493-3") {
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

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

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

	changeSet(author: "Spooky (generated)", id: "1385045672493-4") {
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

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-5") {
		createTable(tableName: "customer") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "customerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

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

	changeSet(author: "Spooky (generated)", id: "1385045672493-6") {
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

	changeSet(author: "Spooky (generated)", id: "1385045672493-7") {
		createTable(tableName: "item") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "itemPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "due_days", type: "bigint")

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "effect_end_date", type: "datetime")

			column(name: "effect_start_date", type: "datetime")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "spec", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "title", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "unit", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-8") {
		createTable(tableName: "item_route") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "item_routePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

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

			column(name: "workstation_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-9") {
		createTable(tableName: "operation") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "operationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

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

	changeSet(author: "Spooky (generated)", id: "1385045672493-10") {
		createTable(tableName: "param") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "paramPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "default_value", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "lower", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "param_type", type: "varchar(255)")

			column(name: "site_id", type: "bigint")

			column(name: "title", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "unit", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "upper", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-11") {
		createTable(tableName: "report") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "reportPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

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

	changeSet(author: "Spooky (generated)", id: "1385045672493-12") {
		createTable(tableName: "report_params") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "report_paramsPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "batch_id", type: "bigint")

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "item_id", type: "bigint")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "operation_id", type: "bigint")

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

	changeSet(author: "Spooky (generated)", id: "1385045672493-13") {
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

	changeSet(author: "Spooky (generated)", id: "1385045672493-14") {
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

	changeSet(author: "Spooky (generated)", id: "1385045672493-15") {
		createTable(tableName: "site") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sitePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

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

	changeSet(author: "Spooky (generated)", id: "1385045672493-16") {
		createTable(tableName: "supplier") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "supplierPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "country", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "site_id", type: "bigint")

			column(name: "tel", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "title", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-17") {
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

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-18") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-19") {
		createTable(tableName: "workstation") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "workstationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "creator", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "editor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

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

	changeSet(author: "Spooky (generated)", id: "1385045672493-20") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-21") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "batch", constraintName: "FK592D73A6C2E8279", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-22") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "batch", constraintName: "FK592D73A6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-23") {
		addForeignKeyConstraint(baseColumnNames: "supplier_id", baseTableName: "batch", constraintName: "FK592D73A365BF459", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "supplier", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-24") {
		addForeignKeyConstraint(baseColumnNames: "batch_id", baseTableName: "batch_report_det", constraintName: "FKCF51E4AD6E07D5BB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "batch", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-25") {
		addForeignKeyConstraint(baseColumnNames: "batch_route_id", baseTableName: "batch_report_det", constraintName: "FKCF51E4ADD78243C4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "batch_route", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-26") {
		addForeignKeyConstraint(baseColumnNames: "report_params_id", baseTableName: "batch_report_det", constraintName: "FKCF51E4AD606AE3A2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "report_params", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-27") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "batch_report_det", constraintName: "FKCF51E4AD6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-28") {
		addForeignKeyConstraint(baseColumnNames: "batch_id", baseTableName: "batch_route", constraintName: "FK109619046E07D5BB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "batch", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-29") {
		addForeignKeyConstraint(baseColumnNames: "operation_id", baseTableName: "batch_route", constraintName: "FK1096190471A4821B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "operation", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-30") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "batch_route", constraintName: "FK109619046B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-31") {
		addForeignKeyConstraint(baseColumnNames: "supplier_id", baseTableName: "batch_route", constraintName: "FK10961904365BF459", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "supplier", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-32") {
		addForeignKeyConstraint(baseColumnNames: "workstation_id", baseTableName: "batch_route", constraintName: "FK1096190416A6A05B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "workstation", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-33") {
		addForeignKeyConstraint(baseColumnNames: "batch_id", baseTableName: "batch_source", constraintName: "FK3E1D8C06E07D5BB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "batch", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-34") {
		addForeignKeyConstraint(baseColumnNames: "child_batch_id", baseTableName: "batch_source", constraintName: "FK3E1D8C01675117E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "batch", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-35") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "batch_source", constraintName: "FK3E1D8C06B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-36") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "customer", constraintName: "FK24217FDE6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-37") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "item", constraintName: "FK317B136B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-38") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "item_route", constraintName: "FK8B96B61D6C2E8279", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-39") {
		addForeignKeyConstraint(baseColumnNames: "operation_id", baseTableName: "item_route", constraintName: "FK8B96B61D71A4821B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "operation", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-40") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "item_route", constraintName: "FK8B96B61D6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-41") {
		addForeignKeyConstraint(baseColumnNames: "workstation_id", baseTableName: "item_route", constraintName: "FK8B96B61D16A6A05B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "workstation", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-42") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "operation", constraintName: "FK631AD5676B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-43") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "param", constraintName: "FK658188D6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-44") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "report", constraintName: "FKC84C55346B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-45") {
		addForeignKeyConstraint(baseColumnNames: "batch_id", baseTableName: "report_params", constraintName: "FKCDF21EF16E07D5BB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "batch", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-46") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "report_params", constraintName: "FKCDF21EF16C2E8279", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-47") {
		addForeignKeyConstraint(baseColumnNames: "operation_id", baseTableName: "report_params", constraintName: "FKCDF21EF171A4821B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "operation", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-48") {
		addForeignKeyConstraint(baseColumnNames: "param_id", baseTableName: "report_params", constraintName: "FKCDF21EF11999F0DB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "param", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-49") {
		addForeignKeyConstraint(baseColumnNames: "report_id", baseTableName: "report_params", constraintName: "FKCDF21EF193BA5719", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "report", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-50") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "report_params", constraintName: "FKCDF21EF16B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-51") {
		addForeignKeyConstraint(baseColumnNames: "supplier_id", baseTableName: "report_params", constraintName: "FKCDF21EF1365BF459", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "supplier", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-52") {
		addForeignKeyConstraint(baseColumnNames: "workstation_id", baseTableName: "report_params", constraintName: "FKCDF21EF116A6A05B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "workstation", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-53") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "supplier", constraintName: "FK9CDBF9CC6B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-54") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46A40216399", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-55") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46AE54C2779", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-56") {
		addForeignKeyConstraint(baseColumnNames: "site_id", baseTableName: "workstation", constraintName: "FK22AA55036B3BA5F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "site", referencesUniqueColumn: "false")
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-57") {
		createIndex(indexName: "FK592D73A365BF459", tableName: "batch") {
			column(name: "supplier_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-58") {
		createIndex(indexName: "FK592D73A6B3BA5F9", tableName: "batch") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-59") {
		createIndex(indexName: "FK592D73A6C2E8279", tableName: "batch") {
			column(name: "item_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-60") {
		createIndex(indexName: "name_unique_1385045672462", tableName: "batch", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-61") {
		createIndex(indexName: "FKCF51E4AD606AE3A2", tableName: "batch_report_det") {
			column(name: "report_params_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-62") {
		createIndex(indexName: "FKCF51E4AD6B3BA5F9", tableName: "batch_report_det") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-63") {
		createIndex(indexName: "FKCF51E4AD6E07D5BB", tableName: "batch_report_det") {
			column(name: "batch_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-64") {
		createIndex(indexName: "FKCF51E4ADD78243C4", tableName: "batch_report_det") {
			column(name: "batch_route_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-65") {
		createIndex(indexName: "unique-report_params_id", tableName: "batch_report_det", unique: "true") {
			column(name: "batch_id")

			column(name: "report_params_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-66") {
		createIndex(indexName: "FK1096190416A6A05B", tableName: "batch_route") {
			column(name: "workstation_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-67") {
		createIndex(indexName: "FK10961904365BF459", tableName: "batch_route") {
			column(name: "supplier_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-68") {
		createIndex(indexName: "FK109619046B3BA5F9", tableName: "batch_route") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-69") {
		createIndex(indexName: "FK109619046E07D5BB", tableName: "batch_route") {
			column(name: "batch_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-70") {
		createIndex(indexName: "FK1096190471A4821B", tableName: "batch_route") {
			column(name: "operation_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-71") {
		createIndex(indexName: "unique-sequence", tableName: "batch_route", unique: "true") {
			column(name: "batch_id")

			column(name: "sequence")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-72") {
		createIndex(indexName: "FK3E1D8C01675117E", tableName: "batch_source") {
			column(name: "child_batch_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-73") {
		createIndex(indexName: "FK3E1D8C06B3BA5F9", tableName: "batch_source") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-74") {
		createIndex(indexName: "FK3E1D8C06E07D5BB", tableName: "batch_source") {
			column(name: "batch_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-75") {
		createIndex(indexName: "unique-child_batch_id", tableName: "batch_source", unique: "true") {
			column(name: "batch_id")

			column(name: "child_batch_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-76") {
		createIndex(indexName: "FK24217FDE6B3BA5F9", tableName: "customer") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-77") {
		createIndex(indexName: "name_unique_1385045672469", tableName: "customer", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-78") {
		createIndex(indexName: "FK317B136B3BA5F9", tableName: "item") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-79") {
		createIndex(indexName: "name_unique_1385045672471", tableName: "item", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-80") {
		createIndex(indexName: "FK8B96B61D16A6A05B", tableName: "item_route") {
			column(name: "workstation_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-81") {
		createIndex(indexName: "FK8B96B61D6B3BA5F9", tableName: "item_route") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-82") {
		createIndex(indexName: "FK8B96B61D6C2E8279", tableName: "item_route") {
			column(name: "item_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-83") {
		createIndex(indexName: "FK8B96B61D71A4821B", tableName: "item_route") {
			column(name: "operation_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-84") {
		createIndex(indexName: "unique-sequence", tableName: "item_route", unique: "true") {
			column(name: "item_id")

			column(name: "sequence")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-85") {
		createIndex(indexName: "FK631AD5676B3BA5F9", tableName: "operation") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-86") {
		createIndex(indexName: "name_unique_1385045672473", tableName: "operation", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-87") {
		createIndex(indexName: "FK658188D6B3BA5F9", tableName: "param") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-88") {
		createIndex(indexName: "name_unique_1385045672475", tableName: "param", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-89") {
		createIndex(indexName: "FKC84C55346B3BA5F9", tableName: "report") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-90") {
		createIndex(indexName: "FKCDF21EF116A6A05B", tableName: "report_params") {
			column(name: "workstation_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-91") {
		createIndex(indexName: "FKCDF21EF11999F0DB", tableName: "report_params") {
			column(name: "param_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-92") {
		createIndex(indexName: "FKCDF21EF1365BF459", tableName: "report_params") {
			column(name: "supplier_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-93") {
		createIndex(indexName: "FKCDF21EF16B3BA5F9", tableName: "report_params") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-94") {
		createIndex(indexName: "FKCDF21EF16C2E8279", tableName: "report_params") {
			column(name: "item_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-95") {
		createIndex(indexName: "FKCDF21EF16E07D5BB", tableName: "report_params") {
			column(name: "batch_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-96") {
		createIndex(indexName: "FKCDF21EF171A4821B", tableName: "report_params") {
			column(name: "operation_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-97") {
		createIndex(indexName: "FKCDF21EF193BA5719", tableName: "report_params") {
			column(name: "report_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-98") {
		createIndex(indexName: "unique-param_id", tableName: "report_params", unique: "true") {
			column(name: "report_id")

			column(name: "param_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-99") {
		createIndex(indexName: "authority_unique_1385045672479", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-100") {
		createIndex(indexName: "name_unique_1385045672480", tableName: "site", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-101") {
		createIndex(indexName: "FK9CDBF9CC6B3BA5F9", tableName: "supplier") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-102") {
		createIndex(indexName: "name_unique_1385045672481", tableName: "supplier", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-103") {
		createIndex(indexName: "username_unique_1385045672483", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-104") {
		createIndex(indexName: "FK143BF46A40216399", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-105") {
		createIndex(indexName: "FK143BF46AE54C2779", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-106") {
		createIndex(indexName: "FK22AA55036B3BA5F9", tableName: "workstation") {
			column(name: "site_id")
		}
	}

	changeSet(author: "Spooky (generated)", id: "1385045672493-107") {
		createIndex(indexName: "name_unique_1385045672484", tableName: "workstation", unique: "true") {
			column(name: "name")
		}
	}
	include file: 'changelog_1.groovy'
}
