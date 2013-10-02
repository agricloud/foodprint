package foodprint

class BatchReportDet extends DefaultTable{

	/*
	* 記錄是哪個批號收集的履歷資料
	*/
	static belongsTo=[batch:Batch]
	/*
	* 記錄收集的項目
	*/
	ReportParams reportParams

	/*
	* 記錄收集項目的值
	*/
	String value


	/*
	* 記錄是哪個途程做的收集
	*/
	// BatchRoute batchRoute




	// 考慮新增後就為獨立的資料收集，若要與 param 同步收集設定，必須刪除後重新加入

	// Report report
	// String name
	// String title
	// String defaultValue="" //預設值
	// ParamType paramType //收集類型
	// String description=""


    /*
    * 收集資料範圍下限
    */
	// String lower=""

    /*
    * 收集資料範圍上限
    */
	// String upper=""


    /*
    * 收集資料值單位
    */
	// String unit=""

    static constraints = {
    	batch(unique: ['reportParams'])
    	value nullable:true
    }
}
