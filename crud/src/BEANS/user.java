package BEANS;

public class user {
	private int id_user;
	private String name;
	private String prenom;
	private String email;
	private String password;
	private static String image;

	public user(int idUser, String name,String prenom, String email, String password, String image) {
		super();
		this.id_user = idUser;
		this.name = name;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.image = image;
	}
	
	public int getId_user() {
		return id_user;
	}
    public String getImage() {
        return image;
    }

    public static void setImage(String image) {
        user.image = image;
    }

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public user( String name,String prenom, String email, String password,String image ) {
		this.name = name;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.image = image;
	}

	public int getIdUser() {
		return id_user;
	}

	public void setIdUser(int idUser) {
		this.id_user = idUser;
	}

	public String getName() {
		return name;
	}
    public String getPassword() {
        return password;
    }
    public String getPrenom() {
        return prenom;
    }

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}