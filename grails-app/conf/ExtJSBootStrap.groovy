import foodprint.*

class ExtJSBootStrap {
    
    def grailsApplication

    def init = { servletContext ->

        environments {
            development {
                // 自動檢查 Ext JS 是否存在
                log.info "Checking Ext JS library ..."
                
                // 取得 WEB-APP 路徑
                def basepath = grailsApplication.mainContext.getResource("/").file
                log.info "Path of WEB-APP directory: ${basepath}"
              
                // 取得 Ext JS 版本設定
                def extjs_ver = grailsApplication.config.extjs.version
                log.info "Configuration specified Ext JS version: ${extjs_ver}"
                
                // 測試 Ant 是否可用
                ant.echo message: 'Testing: Grails Ant Plugin [OK]'

                // 預設的 Ext JS 資料夾是 web-app/extjs
                def extjs_target = new File(basepath, 'extjs')

                // 檢查已經存在的 Ext JS 資料夾
                if (extjs_target.isDirectory()) {
                    log.info "Existing Ext JS found: ${extjs_target}"

                    def exists_ver_file = new File(extjs_target, "extjs-release")
                    def exists_ver = exists_ver_file.isFile()?exists_ver_file.text:''

                    //版本不符合，先刪除舊版資料
                    if (exists_ver != extjs_ver) {
                        log.info "Ext JS require a upgrade progress. remove all existing files first."
                        ant.delete dir: extjs_target
                    }
                }

                // 如果 web-app/extjs 資料夾不存在，進行下載解壓縮動作
                if (!extjs_target.isDirectory()) {

                    // 假如已經有個檔案命名為 extjs 就先刪除
                    if (extjs_target.isFile()) {
                        extjs_target.delete()
                    }

                    // 開始下載程序


                    def extjs_url = "http://cdn.sencha.com/ext/gpl/ext-${extjs_ver}-gpl.zip"
                    log.info "Starting download progress from ${extjs_url} ..."
                    
                    def tmp_file = File.createTempFile('extjs_', '.zip')
                    log.info "Download ${extjs_ver}.zip to ${tmp_file}"
                    ant.get src: extjs_url, dest: tmp_file

                    log.info "Unzip ${tmp_file} to ${basepath} ..."
                    ant.unzip src: tmp_file, dest: basepath

                    basepath.eachFile { _file ->
                        if (_file.isDirectory() && _file.name.startsWith("ext-${extjs_ver}")) {
                            log.info "Rename ${_file.name} to extjs"
                            _file.renameTo(extjs_target)
                        }
                    }

                    // 寫入版本資訊
                    new File(extjs_target, "extjs-release").setText(extjs_ver)
                }

            }
        }
    }
    def destroy = {
    }
}
