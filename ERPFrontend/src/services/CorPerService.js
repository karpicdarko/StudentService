import axios from 'axios';
import AuthService from './AuthService';
import AuthHeader from './AuthHeader';
import CorStuService from '../services/CorStuService';
import StudentService from './StudentService';

const COURSE_SYL_URL = 'http://localhost:8083/api/v1/coursePeriodBySyl/';
const COURSE_SYL_PER_URL = 'http://localhost:8083/api/v1/coursePeriodTest/';

class CorPerService {
	async getCoursesForSyl() {
		const { data: student } = await StudentService.getStudentData();
		console.log('this is STUDENT ' + JSON.stringify(student));
		return axios.get(COURSE_SYL_URL + student.syllabus.id, {
			headers: AuthHeader(),
		});
	}

	async getCoursesForSylPer(period) {
		const { data: student } = await StudentService.getStudentData();
		const { data: courseOS } = await CorStuService.getCorStuData();
		return axios.get(
			COURSE_SYL_PER_URL +
				student.id +
				'/' +
				student.syllabus.id +
				'/' +
				period +
				'/' +
				courseOS.year,
			{
				headers: AuthHeader(),
			}
		);
	}
}

export default new CorPerService();
