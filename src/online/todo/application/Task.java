package online.todo.application;

public class Task {
	private int id = -1;
	private String name = "";
	private String description = "";
	
	public Task() {
		super();
	}
	public Task(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public Task(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
