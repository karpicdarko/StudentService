import axios from 'axios';
import AuthService from './AuthService';
import AuthHeader from './AuthHeader';

const STUDENT_BASE_URL = 'http://localhost:8083/api/v1/syllabusSId/';

const SYLLABUSES_BASE_URL = 'http://localhost:8083/api/v1/syllabus/';

class SyllabusService {
	getSyllabusData() {
		const student = AuthService.getCurrentUser();
		return axios.get(STUDENT_BASE_URL + student.id, { headers: AuthHeader() });
	}

	getAllSyllabuses() {
		return axios.get(SYLLABUSES_BASE_URL, { headers: AuthHeader() });
	}
}

export default new SyllabusService();
