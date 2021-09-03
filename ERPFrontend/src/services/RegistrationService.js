import axios from 'axios';
import AuthService from './AuthService';
import AuthHeader from './AuthHeader';
import StudentService from './StudentService';
import CorStuService from './CorStuService';

const STUDENT_BASE_URL = 'http://localhost:8083/api/v1/registrationPrice/';
const STUDENT_BASE_URL_POST = 'http://localhost:8083/api/v1/registration';
const STUDENTS_REGIS_BASE_URL =
	'http://localhost:8083/api/v1/registrationStudent/';

class RegistrationService {
	getPriceSum() {
		const student = AuthService.getCurrentUser();
		return axios.get(STUDENT_BASE_URL + student.id, { headers: AuthHeader() });
	}

	async getRegistrationsForStudent(period) {
		const { data: student } = await StudentService.getStudentData();
		const { data: corstu } = await CorStuService.getCorStuData();
		console.log(
			'OVO su parametri ' +
				JSON.stringify(student.id) +
				', ' +
				JSON.stringify(corstu.year) +
				', ' +
				period
		);
		// console.log('OVO JE COSTU ' + JSON.stringify(corstu));
		return axios.get(
			STUDENTS_REGIS_BASE_URL + student.id + '/' + corstu.year + '/' + period,
			{
				headers: AuthHeader(),
			}
		);
	}

	async registerExam(cpId, pId, cId) {
		const { data: student } = await StudentService.getStudentData();
		const data = {
			student: { id: student.id },
			coursePeriod: { id: cpId, course: { id: cId }, period: { id: pId } },
		};
		await axios.post(STUDENT_BASE_URL_POST, data, { headers: AuthHeader() });
	}

	async deleteRegistration(id) {
		await axios.delete(STUDENT_BASE_URL_POST + '/' + id, {
			headers: AuthHeader(),
		});
	}
}

export default new RegistrationService();
