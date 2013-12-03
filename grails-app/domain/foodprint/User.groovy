package foodprint

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled=true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	/**
	 * Full Name (Detail)
	 */
	String fullName

	/**
	 * E-Mail
	 */
	String email

	static belongsTo = [
		site: Site		
	]

	static constraints = {
		site nullable: true
		username 	blank: false, unique: true
		password 	blank: false
		fullName 	nullable: true, blank: true
		email 		nullable: true, email: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
