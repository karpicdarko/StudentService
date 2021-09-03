import React, { Component } from 'react';
import StudentService from '../../services/StudentService';
import { Form, Col, Row } from 'react-bootstrap';
import Moment from 'react-moment';

class StudentData extends Component {
	state = {
		student: {},
	};

	componentDidMount() {
		StudentService.getStudentData().then((res) => {
			this.setState({ student: res.data });
		});
	}
	render() {
		const { student } = this.state;
		return (
			<div>
				<h1>Osnovni podaci</h1>
				<hr />
				<Form>
					<Form.Group as={Row} controlId="formPlaintextName">
						<Form.Label column sm="2">
							Ime:
						</Form.Label>
						<Form.Label column sm="4">
							<strong>{student.name}</strong>
						</Form.Label>
						<Form.Label column sm="2">
							Ime roditelja:
						</Form.Label>
						<Form.Label column sm="4">
							<strong>{student.parents_name}</strong>
						</Form.Label>
					</Form.Group>

					<Form.Group as={Row} controlId="formPlaintextEmail">
						<Form.Label column sm="2">
							Prezime:
						</Form.Label>
						<Form.Label column sm="4">
							<strong>{student.surname}</strong>
						</Form.Label>
						<Form.Label column sm="2">
							Pol:
						</Form.Label>
						<Form.Label column sm="4">
							<strong>{student.gender}</strong>
						</Form.Label>
					</Form.Group>

					<Form.Group as={Row} controlId="formPlaintextEmail">
						<Form.Label column sm="2">
							Datum rođenja:
						</Form.Label>
						<Form.Label column sm="4">
							<strong>
								<Moment format="DD.MM.YYYY">{student.birth_date}</Moment>
							</strong>
						</Form.Label>
						<Form.Label column sm="2">
							Adresa:
						</Form.Label>
						<Form.Label column sm="4">
							<strong>{student.address}</strong>
						</Form.Label>
					</Form.Group>

					<Form.Group as={Row} controlId="formPlaintextEmail">
						<Form.Label column sm="2">
							Mesto stanovanja:
						</Form.Label>
						<Form.Label column sm="4">
							<strong>{student.city}</strong>
						</Form.Label>
						<Form.Label column sm="2">
							Mobilni telefon:
						</Form.Label>
						<Form.Label column sm="4">
							<strong>{student.cellphone}</strong>
						</Form.Label>
					</Form.Group>

					<Form.Group as={Row} controlId="formPlaintextEmail">
						<Form.Label column sm="2">
							fiksni telefon:
						</Form.Label>
						<Form.Label column sm="4">
							<strong>{student.landline}</strong>
						</Form.Label>
						<Form.Label column sm="2">
							Email adresa:
						</Form.Label>
						<Form.Label column sm="4">
							<strong>{student.email}</strong>
						</Form.Label>
					</Form.Group>

					<Form.Group as={Row} controlId="formPlaintextEmail">
						<Form.Label column sm="2">
							Srednja škola:
						</Form.Label>
						<Form.Label column sm="4">
							<strong>{student.high_school}</strong>
						</Form.Label>
						<Form.Label column sm="2">
							Datum upisa:
						</Form.Label>
						<Form.Label column sm="4">
							<strong>
								<Moment format="DD.MM.YYYY">{student.enroll_date}</Moment>
							</strong>
						</Form.Label>
					</Form.Group>
				</Form>
			</div>
		);
	}
}

export default StudentData;
