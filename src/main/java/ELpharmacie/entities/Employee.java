package ELpharmacie.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
		public class Employee extends User implements Serializable {

	public Employee(Role roleEmployee) {
		super();
		this.roleEmployee = roleEmployee;
		this.setRoleUser(UserRole.EMPLOYEE);
	}

	public Employee() {
		super();
		this.setRoleUser(UserRole.EMPLOYEE);	}

	@Enumerated(EnumType.STRING)
	private Role roleEmployee;

	private int nbabsenceEmployee;

	public int getNbabsenceEmployee() {
		return nbabsenceEmployee;
	}

	public void setNbabsenceEmployee(int nbabsenceEmployee) {
		this.nbabsenceEmployee = nbabsenceEmployee;
	}
	
	public Role getRoleEmployee() {
		return roleEmployee;
	}

	public void setRoleEmployee(Role roleEmployee) {
		this.roleEmployee = roleEmployee;
	}

	@OneToOne
	private Pool pool;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="employee")
	private List<Claim> claim;

}
