import axios from 'axios';
import AuthService from './AuthService';
import AuthHeader from './AuthHeader';
import StudentService from './StudentService';

const STUDENT_BASE_URL = 'http://localhost:8083/api/v1/course';
const COURSE_SYL_URL = 'http://localhost:8083/api/v1/courseBySylId/';

class CourseService {
	getStudentsData() {
		const student = AuthService.getCurrentUser();
		return axios.get(COURSE_SYL_URL, { headers: AuthHeader() });
	}

	async getStudentData() {
		const { data: student } = await StudentService.getStudentData();
		console.log('this is STUDENT ' + JSON.stringify(student));
		return axios.get(COURSE_SYL_URL + student.syllabus.id, {
			headers: AuthHeader(),
		});
	}
}

export default new CourseService();
