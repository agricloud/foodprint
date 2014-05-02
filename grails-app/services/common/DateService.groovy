package common

import grails.converters.JSON

class DateService {

	static location=foodprint.Country.TAIWAN
	def defaultPattern = 'yyyy-MM-dd HH:mm:ss'

	//取得當地時間(String)
	def getStrDate(){
		def strDate = new Date().format(defaultPattern,getTimeZone())
	}

	//取得指定格式的當地時間(String)
	def getStrDate(format){
		if(format)
			def strdate = new Date().format(format,getTimeZone())
		else
			def strdate = newDate()
	}

	//取得當地時區
	def getTimeZone(){
		if(location==foodprint.Country.TAIWAN)
			return TimeZone.getTimeZone("GMT+8")
	}

	//UTC時間(Date)->當地時間(String) ISO8601格式 ex: 2014-04-01T14:04:01Z
	def formatWithISO8601(date){
		if(date)
			def strDate = date.format("yyyy-MM-dd'T'HH:mm:ss'Z'",getTimeZone())
		else
			null


	}

	//當地時間(String)->UTC時間(Date)
	def parseToUTC(format,strDate){
		//需先將TimeZone預設時區轉換為當地時區 才可使用format轉換為UTC
        TimeZone.setDefault(getTimeZone())
        def date = Date.parse(format,strDate)
		strDate = date.format(defaultPattern,TimeZone.getTimeZone("UTC"))
		TimeZone.setDefault(TimeZone.getTimeZone('UTC'))
		date = Date.parse(defaultPattern,strDate)
		return date
	}

}