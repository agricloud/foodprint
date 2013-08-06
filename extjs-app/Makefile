# extjs make file

# 產生空的 extjs project
extjsappgen: 
	sencha -sdk ~/ext-4.2.0.663/ generate app foodprint ~/projects/foodprint

extjsproduction:
	sencha app build production

extjstesting:
	sencha app build testing

extjsdeploy:
	rsync -a build/foodprint/production/ ../web-app/production/


extjsdone:
	make extjsproduction extjsdeploy