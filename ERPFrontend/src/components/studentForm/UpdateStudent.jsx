import React, { Component } from 'react';

import { Col, Form, Button, Modal } from 'react-bootstrap';
import SyllabusService from '../../services/SyllabusService';
import StudentService from '../../services/StudentService';
import CorStuService from '../../services/CorStuService';
import FinCardService from '../../services/FinCardService';

import 'react-datepicker/dist/react-datepicker.css';

class UpdateStudent extends Component {
	state = {
		student: {
			id: 0,
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
			parents_name: '',
			city: '',
			enroll_date: '',
			syllabus: {
				id: 0,
			},
		},
		syllabuses: [],
		course_of_study: {
			financing_method: '',
			year: 0,
			student: {
				id: 0,
			},
		},
		financial_card: {
			reference_number: '',
			account_balance: 0,
			model: '',
			giro_number: '',
		},
		show: false,
		oldYear: 0,
		oldFinMet: '',
		validated: false,
		validatedNY: false,
	};
	async componentDidMount() {
		const student = await StudentService.getStudent(
			this.props.match.params.id
		).then((res) => {
			this.setState({ student: res.data });
		});
		const finCard = await FinCardService.getFinCard(
			this.props.match.params.id
		).then((res) => {
			this.setState({ financial_card: res.data });
		});
		const cos = await CorStuService.getCorStu(this.props.match.params.id).then(
			(res) => {
				this.setState({
					course_of_study: res.data,
					oldFinMet: res.data.financing_method,
					oldYear: res.data.year,
				});
			}
		);
		const syllabuses = await SyllabusService.getAllSyllabuses().then((res) => {
			this.setState({ syllabuses: res.data });
		});
		const id = await StudentService.getMaxId().then((res) => {
			res.data === null
				? this.setState({ maxId: 0 })
				: this.setState({ maxId: res.data });
		});
		await Promise.all([syllabuses, id, student, finCard]);
	}

	handleSyllChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				syllabus: {
					id: e.target.value,
				},
			},
		}));
	};
	handleCityChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				city: e.target.value,
			},
		}));
	};
	handleParentChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				parents_name: e.target.value,
			},
		}));
	};
	handleSurnameChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				surname: e.target.value,
			},
		}));
	};
	handleLandlineChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				landline: e.target.value,
			},
		}));
	};
	handleIndexChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				index_number: e.target.value,
			},
		}));
	};
	handleHighsChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				high_school: e.target.value,
			},
		}));
	};
	handleGenderChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				gender: e.target.value,
			},
		}));
	};
	handleEmailChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				email: e.target.value,
			},
		}));
	};
	handleNameChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				name: e.target.value,
			},
		}));
	};

	handleAddressChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				address: e.target.value,
			},
		}));
	};

	handleCellChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				cellphone: e.target.value,
			},
		}));
	};

	handleDobChange = (e) => {
		this.setState((prevState) => ({
			student: {
				...prevState.student,
				birth_date: e.target.value,
			},
		}));
	};
	handleBallanceChange = (e) => {
		this.setState((prevState) => ({
			financial_card: {
				...prevState.financial_card,
				account_balance: e.target.value,
			},
		}));
	};
	handleRefNumChange = (e) => {
		this.setState((prevState) => ({
			financial_card: {
				...prevState.financial_card,
				reference_number: e.target.value,
			},
		}));
	};

	handleFinMetChange = (e) => {
		this.setState((prevState) => ({
			course_of_study: {
				...prevState.course_of_study,
				financing_method: e.target.value,
			},
		}));
	};
	handleYearChange = (e) => {
		this.setState((prevState) => ({
			course_of_study: {
				...prevState.course_of_study,
				year: e.target.value,
			},
		}));
	};
	handleSubmit = (event) => {
		const form = event.currentTarget;
		if (form.checkValidity() === false) {
			event.preventDefault();
			event.stopPropagation();
		} else {
			StudentService.updateStudent(this.state.student);
			FinCardService.updateFinCard(this.state.financial_card);
		}
		this.setState({ validated: true });
	};
	handleModal = () => {
		this.setState({ show: !this.state.show });
	};
	handleModalSubmit = (event) => {
		const form = event.currentTarget;
		if (form.checkValidity() === false) {
			event.preventDefault();
			event.stopPropagation();
		} else {
			this.setState((prevState) => ({
				course_of_study: {
					...prevState.course_of_study,
					student: {
						id: this.state.student.id,
					},
				},
			}));
			const courseOfStudy = {
				financing_method: this.state.course_of_study.financing_method,
				year: this.state.course_of_study.year,
				student: {
					id: this.state.course_of_study.student.id,
				},
			};

			CorStuService.postCorStu(courseOfStudy);
		}
		this.setState({ validatedNY: true });
	};
	render() {
		const {
			syllabuses,
			student,
			financial_card,
			course_of_study,
			show,
			oldFinMet,
			oldYear,
			validated,
			validatedNY,
		} = this.state;
		return (
			<div>
				<h1>Izmeni Studenta</h1>
				<h6 onClick={this.handleModal} style={{ cursor: 'pointer' }}>
					Upiši studenta u novu godinu
				</h6>

				<Modal show={show} onHide={this.handleModal}>
					<Modal.Header closeButton>
						<Modal.Title>Upiši studenta u novu godinu</Modal.Title>
					</Modal.Header>
					<Modal.Body>
						<Form
							noValidate
							validated={validatedNY}
							onSubmit={this.handleModalSubmit}
						>
							<Form.Row>
								<Form.Group as={Col} md="4" controlId="validationCustom01">
									<Form.Label>Trenutna godina</Form.Label>
									<Form.Control
										type="text"
										readOnly={true}
										value={oldYear}
										required
									/>
								</Form.Group>
								<Form.Group as={Col} md="5" controlId="validationCustom02">
									<Form.Label>Trenutni način finansiranja</Form.Label>

									<Form.Control
										type="text"
										readOnly={true}
										value={oldFinMet}
										required
									/>
								</Form.Group>
							</Form.Row>
							<Form.Row>
								<Form.Group as={Col} md="4" controlId="validationCustom01">
									<Form.Label>Nova Godina</Form.Label>
									<Form.Control
										as="select"
										required
										onChange={this.handleYearChange}
									>
										<option value={''}>Odaberi...</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
									</Form.Control>
									<Form.Control.Feedback type="invalid">
										Odaberite godinu!
									</Form.Control.Feedback>
								</Form.Group>
								<Form.Group as={Col} md="5" controlId="validationCustom02">
									<Form.Label>Novi način finansiranja</Form.Label>

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
									<Form.Control.Feedback type="valid">
										Looks good!
									</Form.Control.Feedback>
								</Form.Group>
							</Form.Row>
							<Modal.Footer>
								<Button variant="secondary" onClick={this.handleModal}>
									Zatvori
								</Button>
								<Button variant="primary" type="submit">
									Sačuvaj
								</Button>
							</Modal.Footer>
						</Form>
					</Modal.Body>
				</Modal>
				<Form noValidate validated={validated} onSubmit={this.handleSubmit}>
					<Form.Row>
						<Form.Group as={Col} md="3" controlId="validationCustom01">
							<Form.Label>Ime</Form.Label>
							<Form.Control
								type="text"
								value={student.name}
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
								value={student.surname}
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
								value={student.index_number}
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
								value={student.email}
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
								value={student.birth_date}
								required
								onChange={this.handleDobChange}
							/>
							<Form.Control.Feedback type="invalid">
								Unesite datum rođenja!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom04">
							<Form.Label>Srednja škola</Form.Label>
							<Form.Control
								type="text"
								value={student.high_school}
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
								value={student.address}
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
								value={student.city}
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
								value={student.gender}
								required
								onChange={this.handleGenderChange}
							>
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
								value={student.parents_name}
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
								value={student.cellphone}
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
								value={student.landline}
								required
								onChange={this.handleLandlineChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite broj fiksnog telefona!
							</Form.Control.Feedback>
						</Form.Group>
					</Form.Row>
					<Form.Row>
						<Form.Group as={Col} md="6" controlId="validationCustom03">
							<Form.Label>Smer</Form.Label>
							<Form.Control
								as="select"
								value={student.syllabus.id}
								required
								onChange={this.handleSyllChange}
							>
								<option value={''}>Odaberite...</option>
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
						<Form.Group as={Col} md="3" controlId="validationCustom05">
							<Form.Label>Personalni poziv na broj</Form.Label>
							<Form.Control
								type="text"
								value={financial_card.reference_number}
								required
								onChange={this.handleRefNumChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite personalni poziv na broj!
							</Form.Control.Feedback>
						</Form.Group>
						<Form.Group as={Col} md="3" controlId="validationCustom05">
							<Form.Label>Stanje na računu</Form.Label>
							<Form.Control
								type="text"
								value={financial_card.account_balance}
								required
								onChange={this.handleBallanceChange}
							/>
							<Form.Control.Feedback type="invalid">
								Upišite stanje na računu!
							</Form.Control.Feedback>
						</Form.Group>
					</Form.Row>
					<Form.Row>
						<Form.Group as={Col} md="3" controlId="validationCustom03">
							<Form.Label>Godina</Form.Label>
							<Form.Control
								type="text"
								value={oldYear}
								required
								readOnly={true}
							/>
						</Form.Group>
						<Form.Group as={Col} md="4" controlId="validationCustom04">
							<Form.Label>Način finansiranja</Form.Label>
							<Form.Control
								type="text"
								readOnly={true}
								value={oldFinMet}
								required
								onChange={this.handleParentChange}
							/>
						</Form.Group>
					</Form.Row>
					<Button type="submit">Sačuvaj</Button>
				</Form>
			</div>
		);
	}
}

export default UpdateStudent;
