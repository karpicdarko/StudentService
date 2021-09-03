import axios from 'axios';
import AuthService from './AuthService';
import AuthHeader from './AuthHeader';

const STUDENT_BASE_URL = 'http://localhost:8083/api/v1/financialCardSId/';
const FINCARD_ADD_URL = 'http://localhost:8083/api/v1/financialCard';
const FINCARD_ADD_MONEY = 'http://localhost:8083/api/v1/financialCardMoney/';

class FinCardService {
	getStudentFinancials() {
		const student = AuthService.getCurrentUser();
		return axios.get(STUDENT_BASE_URL + student.id, { headers: AuthHeader() });
	}

	getFinCard(id) {
		return axios.get(STUDENT_BASE_URL + id, { headers: AuthHeader() });
	}
	postFinCard(finCard) {
		axios.post(FINCARD_ADD_URL, finCard, { headers: AuthHeader() });
	}
	updateFinCard(finCard) {
		axios.put(FINCARD_ADD_URL, finCard, { headers: AuthHeader() });
	}
	addMoney(id) {
		axios.get(FINCARD_ADD_MONEY + id, { headers: AuthHeader() });
	}
}

export default new FinCardService();
