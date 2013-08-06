/*
 * 類似多公司別的資料表，以往在進行多公司別處理時都為拆開的資料庫
 * 在此，我們使用資料表來區分，所以一旦涉及可能需要區分公司別的資料表，皆會有 site 作為識別
 */



package foodprint

class Site {

	String name
	String title
	String description
	String address

	// Site site
	String editor=""	//修改者
	String creator=""	//建立者
	Date dateCreated    //建立日期
	Date lastUpdated    //修改日期

    static constraints = {
    	name unique:true
    }
}
