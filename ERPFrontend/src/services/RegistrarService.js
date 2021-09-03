import axios from 'axios';
import AuthService from './AuthService';
import AuthHeader from './AuthHeader';

const REGISTRAR_BASE_URL = 'http://localhost:8083/api/v1/registrar/';

class RegistrarService {
	getRegistrarData() {
		const registrar = AuthService.getCurrentUser();
		return axios.get(REGISTRAR_BASE_URL + registrar.id, {
			headers: AuthHeader(),
		});
	}
}

export default new RegistrarService();
