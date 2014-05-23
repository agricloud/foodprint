package common

import grails.converters.JSON

class DateService {

	static location=foodprint.Country.TAIWAN
	def defaultPattern = 'yyyy-MM-dd HH:mm:ss'

	//取得當地目前時間(String)
	def getStrDate(){
		def strDate = new Date().format(defaultPattern,getTimeZone())
	}

	//取得指定格式的當地目前時間(String)
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

	//將指定的UTC時間(Date)->轉換為當地時間(String) ISO8601格式 ex: 2014-04-01T14:04:01Z
	def formatWithISO8601(date){
		if(date)
			def strDate = date.format("yyyy-MM-dd'T'HH:mm:ss'Z'",getTimeZone())
		else
			null


	}

	//將指定的當地時間(String)->轉換為UTC時間(Date)
	def parseToUTC(format,strDate){
		//需先將TimeZone預設時區轉換為當地時區 才可使用format轉換為UTC
        TimeZone.setDefault(getTimeZone())
        def date = Date.parse(format,strDate)
		strDate = date.format(defaultPattern,TimeZone.getTimeZone("UTC"))
		TimeZone.setDefault(TimeZone.getTimeZone('UTC'))
		date = Date.parse(defaultPattern,strDate)
		return date
	}

	//將指定的當地時間(String) ISO8601格式->轉換為UTC時間(Date)
	def parseISO8601ToUTC(strDate){
		return parseToUTC("yyyy-MM-dd'T'HH:mm:ss'Z'",strDate)
	}


}