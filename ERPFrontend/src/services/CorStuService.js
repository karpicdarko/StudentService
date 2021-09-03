import axios from 'axios';
import AuthService from './AuthService';
import AuthHeader from './AuthHeader';

const STUDENT_BASE_URL = 'http://localhost:8083/api/v1/courseOfStudySIdYear/';
const CORSTU_ADD_URL = 'http://localhost:8083/api/v1/courseOfStudy';
const CORSTU_ADD_URL2 = 'http://localhost:8083/api/v1/courseOfStudySId';

class CorStuSevice {
	getCorStuData() {
		const student = AuthService.getCurrentUser();
		return axios.get(STUDENT_BASE_URL + student.id, { headers: AuthHeader() });
	}
	getCorStu(id) {
		return axios.get(STUDENT_BASE_URL + id, { headers: AuthHeader() });
	}
	getAllCorStu() {
		const student = AuthService.getCurrentUser();
		return axios.get(CORSTU_ADD_URL2 + '/' + student.id, {
			headers: AuthHeader(),
		});
	}
	postCorStu(corStu) {
		axios.post(CORSTU_ADD_URL, corStu, { headers: AuthHeader() });
	}
}

export default new CorStuSevice();
