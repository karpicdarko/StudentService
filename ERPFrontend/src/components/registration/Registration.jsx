import React, { Component } from 'react';
import './Registration.css';
import CourseService from '../../services/CorPerService';
import RegistrationService from '../../services/RegistrationService';
import PeriodService from '../../services/PeriodService';
import { Button, Modal } from 'react-bootstrap';

class Registration extends Component {
	state = {
		courses: [],
		periods: [],
		oldPeriod: '',
		loaded: false,
		alert: false,
		error: '',
	};

	async componentDidMount() {
		// const course = await CourseService.getCoursesForSyl().then((res) => {
		// 	this.setState({ courses: res.data });
		// });
		const periods = await PeriodService.getAllActive().then((res) => {
			this.setState({ periods: res.data });
		});

		await Promise.all([periods]);
		this.setState({ loaded: true });
	}

	periodChange = async (event) => {
		this.setState({ oldPeriod: event.target.value });
		const course = await CourseService.getCoursesForSylPer(
			event.target.value
		).then((res) => {
			this.setState({ courses: res.data });
		});

		await Promise.all([course]);
	};

	courseRegistered = async (period) => {
		await CourseService.getCoursesForSylPer(period).then((res) => {
			this.setState({ courses: res.data });
		});
	};
	showAlert = () => {
		this.setState({ alert: !this.state.alert });
	};

	registerExam = async (cpId, pId, cId, period) => {
		const reg = await RegistrationService.registerExam(cpId, pId, cId).catch(
			(error) => {
				if (error.response) {
					this.setState({ error: error.response.data });
					this.showAlert();
				}
			}
		);
		await Promise.all([reg]);
		this.courseRegistered(period);
	};

	render() {
		const { courses, periods, loaded, error, alert } = this.state;
		return (
			<div>
				<Modal show={alert} onHide={this.showAlert}>
					<Modal.Header closeButton>
						<Modal.Title>Nije moguća prijava</Modal.Title>
					</Modal.Header>
					<Modal.Body>{error}</Modal.Body>
					<Modal.Footer>
						<Button variant="secondary" onClick={this.showAlert}>
							U redu
						</Button>
					</Modal.Footer>
				</Modal>
				{loaded && (
					<div>
						<div onChange={this.periodChange} className="periods">
							{periods.length === 0 ? (
								<div>Nema aktivnih rokova</div>
							) : (
								periods.map((period) => (
									<div key={period.id}>
										<input type="radio" value={period.name} name="period" />
										<label> {period.name}</label>
									</div>
								))
							)}
						</div>
						<table className="table">
							<thead className="thead-dark">
								<tr>
									<th scope="col">Oznaka</th>
									<th scope="col">Naziv</th>
									<th scope="col">Datum</th>
									{/* <th scope="col">Cena</th> */}
									<th scope="col">Nastavnik</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								{courses.map((course) => (
									<tr key={course.id}>
										<td>{course.course.mark}</td>
										<td>{course.course.name}</td>
										<td>{course.date}</td>
										{/* <td>{course.price}</td> */}
										<td>
											{course.course.professor.name}{' '}
											{course.course.professor.surname}
										</td>
										<td>
											<button
												type="button"
												className="btn btn-outline-dark"
												onClick={() =>
													this.registerExam(
														course.id,
														course.period.id,
														course.course.id,
														course.period.name
													)
												}
											>
												Prijavi
											</button>
										</td>
									</tr>
								))}
							</tbody>
						</table>
					</div>
				)}
			</div>
		);
	}
}

export default Registration;
