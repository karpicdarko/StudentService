import React, { Component } from 'react';
import PeriodService from '../../services/PeriodService';
import RegistrationService from '../../services/RegistrationService';

class Unregister extends Component {
	state = {
		courses: [],
		periods: [],
		loaded: false,
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
		const course = await RegistrationService.getRegistrationsForStudent(
			event.target.value
		).then((res) => {
			this.setState({ courses: res.data });
			console.log('Ovo je course ' + JSON.stringify(res.data));
		});

		await Promise.all([course]);
	};

	courseUnregistered = async (period) => {
		await RegistrationService.getRegistrationsForStudent(period).then((res) => {
			this.setState({ courses: res.data });
		});
		console.log('ZAVRSIO SAM PONOVNO ODJAVLJIVANJE');
	};

	unregisterExam = async (registrationId, period) => {
		const unreg = await RegistrationService.deleteRegistration(registrationId);
		await Promise.all([unreg]);
		this.courseUnregistered(period);
	};

	render() {
		const { courses, periods, loaded } = this.state;
		return (
			<div>
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
									<th scope="col">Vreme</th>
									{/* <th scope="col">Učionica</th> */}
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								{courses.map((course) => (
									<tr key={course.id}>
										<td>{course.coursePeriod.course.mark}</td>
										<td>{course.coursePeriod.course.name}</td>
										<td>{course.coursePeriod.date}</td>
										{/* <td>{course.price}</td> */}
										<td>
											{course.coursePeriod.course.professor.name}{' '}
											{course.coursePeriod.course.professor.surname}
										</td>
										<td>{course.coursePeriod.time_of}</td>
										{/* <td>{course.classroom}</td> */}
										<td>
											<button
												type="button"
												className="btn btn-outline-dark"
												onClick={() =>
													this.unregisterExam(
														course.id,
														course.coursePeriod.period.name
													)
												}
											>
												Odjavi
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

export default Unregister;
