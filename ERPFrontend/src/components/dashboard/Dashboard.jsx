import React, { Component } from 'react';
import './Dashboard.css';
import Moment from 'react-moment';
import { PayPalScriptProvider, PayPalButtons } from '@paypal/react-paypal-js';
import { CalendarComponent } from '@syncfusion/ej2-react-calendars';
import FinCardService from '../../services/FinCardService';
import StudentService from '../../services/StudentService';
import CorStuService from '../../services/CorStuService';
import SyllabusService from '../../services/SyllabusService';
import RegistrationService from '../../services/RegistrationService';
import PeriodService from '../../services/PeriodService';

class Dashboard extends Component {
	state = {
		finCard: {},
		corStu: {},
		periods: [],
		priceSum: null,
		syll: {},
		student: {},
		loaded: false,
	};
	async componentDidMount() {
		const student = await StudentService.getStudentData().then((res) => {
			this.setState({ student: res.data });
		});
		const card = await FinCardService.getStudentFinancials().then((res) => {
			this.setState({ finCard: res.data });
		});
		const corStu = await CorStuService.getCorStuData().then((res) => {
			this.setState({ corStu: res.data });
		});
		const syllabus = await SyllabusService.getSyllabusData().then((res) => {
			this.setState({ syll: res.data });
		});
		const priceSum = await RegistrationService.getPriceSum().then((res) => {
			this.setState({ priceSum: res.data });
		});
		const periods = await PeriodService.getActivePeriod().then((res) => {
			this.setState({ periods: res.data });
		});
		await Promise.all([student, card, corStu, syllabus, priceSum, periods]);
		this.setState({ loaded: true });
	}
	addMoney = (id) => {
		FinCardService.addMoney(id);
		window.location.reload();
	};

	render() {
		const { finCard, student, corStu, syll, priceSum, periods, loaded } =
			this.state;
		const initialOptions = {
			'client-id':
				'AUiOI4KcIcsRQHGOMl-s0bdDfMj4bbEMi2ySnkhkYohpEgdOlB9xwjmSGfcH0H9Foa8ZhAt4oRy_dq8K',
			currency: 'USD',
		};
		return (
			<div className="dash">
				{loaded && (
					<div className="grid-container">
						<div className="card1">
							<div className="card_title">
								<h6>Podaci o uplati</h6>
							</div>
							<div className="card_data">
								<div className="card_data_left">Žiro račun</div>
								<div className="card_data_right">{finCard.giro_number}</div>
								<div className="card_data_left">Broj modela</div>
								<div className="card_data_right">{finCard.model}</div>
								<div className="card_data_left">Poziv na broj</div>
								<div className="card_data_right">
									{finCard.reference_number}
								</div>
							</div>
						</div>
						<div className="card2">
							<div className="card_title">
								<h6>Finansijska kartica</h6>
							</div>
							<div className="card_data">
								{/* <div className="card_data_left">Ukupne uplate</div>
				<div className="card_data_right">Nesto</div> */}
								<div className="card_data_left">Ukupni troškovi</div>
								<div className="card_data_right">
									{priceSum === null ? 0 : priceSum * 200} RSD
								</div>
								<div className="card_data_left">Raspoloživa sredstva</div>
								<div className="card_data_right">
									{finCard.account_balance} RSD
								</div>
								<PayPalScriptProvider options={initialOptions}>
									<div className="paypal" style={{ maxWidth: '80px' }}>
										<PayPalButtons
											createOrder={(data, actions) => {
												return actions.order.create({
													intent: 'CAPTURE',
													purchase_units: [
														{
															description: 'Uplata za prijavu ispita',
															amount: {
																currency_code: 'USD',
																value: 500.0,
															},
														},
													],
												});
											}}
											onApprove={() => this.addMoney(student.id)}
											style={{
												color: 'black',
												layout: 'horizontal',
												height: 25,
											}}
										/>
									</div>
								</PayPalScriptProvider>
							</div>
						</div>
						<div className="card3">
							<div className="card_title">
								<h6>Aktivni rokovi</h6>
							</div>
							<div className="card_data">
								{periods.length === 0 ? (
									<div className="card_data_left"> Nema aktivnih rokova </div>
								) : (
									periods.map((period) => (
										<div key={period.id} className="card_data">
											<div className="card_data_left">{period.name}</div>
											<div className="card_data_right">
												<Moment format="DD">{period.startDate}</Moment>-
												<Moment format="DD.MM.YYYY">{period.endDate}</Moment>
											</div>
										</div>
									))
								)}
							</div>
						</div>
						<div className="card4">
							<CalendarComponent
								isMultiSelection={true}
								// values={dates}
							></CalendarComponent>
						</div>
						<div className="card5">
							<div className="card_title">
								<h6>Podaci o studijama</h6>
							</div>
							<div className="card5_cards">
								<div className="card51">
									<p>Studijski program</p>
									<h4>{syll.name}</h4>
								</div>

								<div className="card52">
									<p>Godina upisa</p>
									<h4>
										<Moment format="YYYY">{student.enroll_date}</Moment>
									</h4>
								</div>

								<div className="card53">
									<p>Način finansiranja</p>
									<h4>{corStu.financing_method}</h4>
								</div>

								<div className="card54">
									<p>Godina studija</p>
									<h4>{corStu.year}</h4>
								</div>
							</div>
						</div>
					</div>
				)}
			</div>
		);
	}
}

export default Dashboard;
