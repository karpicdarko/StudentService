import React, { Component, useState } from 'react';
import moment from 'moment';
import { Col, Form, InputGroup, Button } from 'react-bootstrap';
import SyllabusService from '../../services/SyllabusService';
import StudentService from '../../services/StudentService';
import CorStuService from '../../services/CorStuService';
import FinCardService from '../../services/FinCardService';

import 'react-datepicker/dist/react-datepicker.css';

class AddStudent extends Component {
	state = {
		name: '',
		address: '',
		cellphone: '',
		birth_date: '',
		email: '',
		gender: '',
		high_school: '',
		index_number: '',
		landline: '',
		surname: '',
		password: '',
		parents_name: '',
		city: '',
		syllabusId: null,
		syllabuses: [],
		financing_method: '',
		ref_number: '',
		maxId: null,
		validated: false,
		reload: true,
	};
	async componentDidMount() {
		const syllabuses = await SyllabusService.getAllSyllabuses().then((res) => {
			this.setState({ syllabuses: res.data });
		});

		await Promise.all([syllabuses]);
	}
	// var [(validated, setValidated)] = useState(false));
	handleFinMetChange = (e) => {
		this.setState({ financing_method: e.target.value });
	};
	handleRefNumChange = (e) => {
		this.setState({ ref_number: e.target.value });
	};
	handleSyllChange = (e) => {
		this.setState({ syllabusId: e.target.value });
	};
	handleCityChange = (e) => {
		this.setState({ city: e.target.value });
	};
	handleParentChange = (e) => {
		this.setState({ parents_name: e.target.value });
	};
	handlePasswordChange = (e) => {
		this.setState({ password: e.target.value });
	};
	handleSurnameChange = (e) => {
		this.setState({ surname: e.target.value });
	};
	handleLandlineChange = (e) => {
		this.setState({ landline: e.target.value });
	};
	handleIndexChange = (e) => {
		this.setState({ index_number: e.target.value });
	};
	handleHighsChange = (e) => {
		this.setState({ high_school: e.target.value });
	};
	handleGenderChange = (e) => {
		this.setState({ gender: e.target.value });
	};
	handleEmailChange = (e) => {
		this.setState({ email: e.target.value });
	};
	handleNameChange = (e) => {
		this.setState({ name: e.target.value });
	};

	handleAddressChange = (e) => {
		this.setState({ address: e.target.value });
	};

	handleCellChange = (e) => {
		this.setState({ cellphone: e.target.value });
	};

	handleDobChange = (e) => {
		this.setState({ birth_date: e.target.value });
	};
	handleSubmit = async (event) => {
		event.preventDefault();
		const form = event.currentTarget;
		if (form.checkValidity() === false) {
			event.preventDefault();
			event.stopPropagation();
		} else {
			const student = {
				name: this.state.name,
				address: this.state.address,
				cellphone: this.state.cellphone,
				birth_date: this.state.birth_date,
				email: this.state.email,
				gender: this.state.gender,
				high_school: this.state.high_school,
				username: this.state.index_number,
				landline: this.state.landline,
				surname: this.state.surname,
				password: this.state.password,
				parents_name: this.state.parents_name,
				city: this.state.city,
				enroll_date: moment(new Date()).format('YYYY-MM-DD'),
				syllabus: {
					id: this.state.syllabusId,
				},
			};
			console.log('Sad upisujem student');
			const post = await StudentService.postStudent(student);
			await Promise.all([post]);
			console.log('Sad trazim id');
			const id = await StudentService.getMaxId().then((res) => {
				res.data === null
					? this.setState({ maxId: 1 })
					: this.setState({ maxId: res.data });
				console.log('Ovo je res ' + JSON.stringify(res.data));
			});
			await Promise.all([id]);
			const finc = await this.postFinCard(this.state.maxId);
			const cos = await this.postCourseOS(this.state.maxId);
			await Promise.all([finc, cos]);
		}
		this.setState({ validated: true });
		window.location.reload();

		// console.log('Ispisi mi studenta: ' + JSON.stringify(student));
	};

	postFinCard = (studentId) => {
		console.log('Ovo je student id ' + studentId);
		const finCard = {
			account_balance: 0,
			giro_number: '840-1710666-12',
			model: '97',
			reference_number: this.state.ref_number,
			student: {
				id: studentId,
			},
		};
		console.log('OVO JE FIN CARD ' + JSON.stringify(finCard));
		FinCardService.postFinCard(finCard);
	};

	postCourseOS = (studentId) => {
		const courseOS = {
			financing_method: this.state.financing_method,
			year: 1,
			student: {
				id: studentId,
			},
		};
		console.log('OVO JE COOS ' + JSON.stringify(courseOS));
		CorStuService.postCorStu(courseOS);
	};

	render() {
		const { syllabuses, validated } = this.state;
		return (
			<div>
				<h1>Dodaj Studenta</h1>
				<Form noValidate validated={validated} onSubmit={this.handleSubmit}>
					<Form.Row>
						<Form.Group as={Col} md="3" controlId="validationCustom01">
							<Form.Label>Ime</Form.Label>
							<Form.Control
								type="text"
								required
								onChange={this.handleNameChange}
							/>

							<Form.Control.Feedback type="invalid">
								Upišite ime!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom02">
							<Form.Label>Prezime</Form.Label>

							<Form.Control
								type="text"
								required
								onChange={this.handleSurnameChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite prezime!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustomUsername">
							<Form.Label>Broj indeksa</Form.Label>

							<Form.Control
								type="text"
								required
								onChange={this.handleIndexChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite broj indeksa!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom02">
							<Form.Label>Email</Form.Label>

							<Form.Control
								type="text"
								required
								onChange={this.handleEmailChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite email!
							</Form.Control.Feedback>
						</Form.Group>
					</Form.Row>
					<Form.Row>
						<Form.Group as={Col} md="3" controlId="validationCustom03">
							<Form.Label>Datum rođenja</Form.Label>
							<Form.Control
								type="date"
								required
								onChange={this.handleDobChange}
							/>
							<Form.Control.Feedback type="invalid">
								Odaberite datum rođenja!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom04">
							<Form.Label>Srednja škola</Form.Label>
							<Form.Control
								type="text"
								required
								onChange={this.handleHighsChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite naziv srednje škole!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom05">
							<Form.Label>Adresa</Form.Label>
							<Form.Control
								type="text"
								required
								onChange={this.handleAddressChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite adresu!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom05">
							<Form.Label>Grad</Form.Label>
							<Form.Control
								type="text"
								required
								onChange={this.handleCityChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite naziv grada!
							</Form.Control.Feedback>
						</Form.Group>
					</Form.Row>
					<Form.Row>
						<Form.Group as={Col} md="3" controlId="validationCustom03">
							<Form.Label>Pol</Form.Label>
							<Form.Control
								as="select"
								required
								onChange={this.handleGenderChange}
							>
								<option value={''}>Odaberi...</option>
								<option value="Muško">Muško</option>
								<option value="Žensko">Žensko</option>
							</Form.Control>
							<Form.Control.Feedback type="invalid">
								Odaberite pol!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom04">
							<Form.Label>Ime roditelja</Form.Label>
							<Form.Control
								type="text"
								required
								onChange={this.handleParentChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite ime roditelja!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom05">
							<Form.Label>Mobilni</Form.Label>
							<Form.Control
								type="text"
								required
								onChange={this.handleCellChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite broj mobilnog telefona!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom05">
							<Form.Label>Fiksni</Form.Label>
							<Form.Control
								type="text"
								required
								onChange={this.handleLandlineChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite broj fiksnog telefona!
							</Form.Control.Feedback>
						</Form.Group>
					</Form.Row>
					<Form.Row>
						<Form.Group as={Col} md="3" controlId="validationCustom03">
							<Form.Label>Smer</Form.Label>
							<Form.Control
								as="select"
								placeholder="Smer"
								required
								onChange={this.handleSyllChange}
							>
								<option value={''}>Odaberi...</option>
								{syllabuses.map((syllabus) => (
									<option key={syllabus.id} value={syllabus.id}>
										{syllabus.name}
									</option>
								))}
							</Form.Control>
							<Form.Control.Feedback type="invalid">
								Odaberite smer!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom04">
							<Form.Label>Šifra</Form.Label>
							<Form.Control
								type="text"
								required
								onChange={this.handlePasswordChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite šifru!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom05">
							<Form.Label>Način finansiranja</Form.Label>
							<Form.Control
								as="select"
								required
								onChange={this.handleFinMetChange}
							>
								<option value={''}>Odaberi...</option>
								<option value="Budžet">Budžet</option>
								<option value="Samofinansiranje">Samofinansiranje</option>
							</Form.Control>
							<Form.Control.Feedback type="invalid">
								Odaberite način finansiranja!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom05">
							<Form.Label>Personalni poziv na broj</Form.Label>
							<Form.Control
								type="text"
								required
								onChange={this.handleRefNumChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite personalni poziv na broj!
							</Form.Control.Feedback>
						</Form.Group>
					</Form.Row>
					<Button type="submit">Sačuvaj</Button>
				</Form>
			</div>
		);
	}
}

export default AddStudent;
