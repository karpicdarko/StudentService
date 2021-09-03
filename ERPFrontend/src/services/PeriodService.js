import axios from 'axios';
import AuthHeader from './AuthHeader';

const STUDENT_BASE_URL = 'http://localhost:8083/api/v1/periodActive/';
const PERIOD_BASE_URL = 'http://localhost:8083/api/v1/allPeriodsActive/';
const ALL_PERIOD_BASE_URL = 'http://localhost:8083/api/v1/period/';

class PeriodService {
	getActivePeriod() {
		return axios.get(STUDENT_BASE_URL, { headers: AuthHeader() });
	}

	getAllActive() {
		return axios.get(PERIOD_BASE_URL, { headers: AuthHeader() });
	}

	getAllPeriods() {
		return axios.get(ALL_PERIOD_BASE_URL, { headers: AuthHeader() });
	}

	getById(period) {
		return axios.get(ALL_PERIOD_BASE_URL + period, {
			headers: AuthHeader(),
		});
	}

	addPeriod(period) {
		console.log('Dodajem rok ' + JSON.stringify(period));
		axios.post(ALL_PERIOD_BASE_URL, period, { headers: AuthHeader() });
	}

	updatePeriod(period) {
		axios.put(ALL_PERIOD_BASE_URL, period, { headers: AuthHeader() });
	}

	async deletePeriod(period) {
		await axios.delete(ALL_PERIOD_BASE_URL + period, { headers: AuthHeader() });
	}
}

export default new PeriodService();
