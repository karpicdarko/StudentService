import axios from 'axios';
import AuthService from './AuthService';
import AuthHeader from './AuthHeader';

const STUDENT_BASE_URL = 'http://localhost:8083/api/v1/student/';
const STUDENT_ADD = 'http://localhost:8083/api/auth/signup';
const STUDENT_MAX_ID = 'http://localhost:8083/api/v1/studentMaxId';

class StudentService {
	getStudentData() {
		const student = AuthService.getCurrentUser();
		return axios.get(STUDENT_BASE_URL + student.id, { headers: AuthHeader() });
	}
	getStudent(id) {
		return axios.get(STUDENT_BASE_URL + id, { headers: AuthHeader() });
	}
	getAllStudents() {
		return axios.get(STUDENT_BASE_URL, { headers: AuthHeader() });
	}
	getMaxId() {
		return axios.get(STUDENT_MAX_ID, { headers: AuthHeader() });
	}
	async postStudent(student) {
		console.log('Ovo upisujem u bazu ' + JSON.stringify(student));
		const prom = await axios.post(STUDENT_ADD, student, {
			headers: AuthHeader(),
		});
		await Promise.all([prom]);
	}

	updateStudent(student) {
		axios.put(STUDENT_BASE_URL, student, { headers: AuthHeader() });
	}

	async deleteStudent(id) {
		await axios.delete(STUDENT_BASE_URL + id, { headers: AuthHeader() });
	}
}

export default new StudentService();
