package foodprint

class FileHandleService {

    def checkAndCreate(File storagePathDirectory) {
      if (!storagePathDirectory.exists()) {
        if (storagePathDirectory.mkdirs()) {
          log.info " folder create SUCCESS"
        } else {
          log.info " folder create FAILED"
        }
      }
    }
}
