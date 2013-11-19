package common

import grails.converters.JSON

class EnumService {

	def messageSource

	def values(enumClass){//ex :foodprint.Country
		def className = enumClass.toString().replace('class foodprint.','')
		className = className[0].toLowerCase() + className[1..-1]

		def enumClassAry = enumClass.values()
		def result = []
		Object[] obj
        enumClassAry.each{
        	def enumClassJson = [:]
        	enumClassJson.title = messageSource.getMessage(className+'.'+it+'.label',obj, Locale.getDefault())
        	enumClassJson.name = it.name()

        	result << enumClassJson
        }

        result
	}

	def name(enumInstance){//ex: Country country
		def className=enumInstance.class.toString().replace('class foodprint.','')
		className = className[0].toLowerCase() + className[1..-1]
		def enumInstanceJson =[:]
		Object[] obj 
		enumInstanceJson.name = enumInstance.name()
		enumInstanceJson.title = messageSource.getMessage(className+'.'+enumInstance.name()+'.label',obj, Locale.getDefault())

		enumInstanceJson
	}
}