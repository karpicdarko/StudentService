import React, { Component } from 'react';
import './AllPeriods.css';
import PeriodService from '../../services/PeriodService';
import { Col, Form, Button, Modal } from 'react-bootstrap';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import ToolkitProvider, { Search } from 'react-bootstrap-table2-toolkit';
import Moment from 'react-moment';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

class AllPeriods extends Component {
	state = {
		periods: [],
		showAdd: false,
		showUpdate: false,
		period: {
			active: 0,
			endDate: '',
			startDate: '',
			name: '',
		},
		periodUpdate: {
			id: 0,
			active: 0,
			endDate: '',
			startDate: '',
			name: '',
		},
		validated: false,
		validatedUp: false,
		deleteOpen: false,
		row: {},
	};

	async componentDidMount() {
		const periods = await PeriodService.getAllPeriods().then((res) => {
			this.setState({ periods: res.data });
		});

		await Promise.all([periods]);
	}

	handleModalAdd = () => {
		this.setState({ showAdd: !this.state.showAdd });
	};
	modalUpdateSetShow = () => {
		this.setState({ showUpdate: !this.state.showUpdate });
	};
	handleModalUpdate = async (row) => {
		const period = await PeriodService.getById(row.id).then((res) => {
			this.setState({ periodUpdate: res.data });
		});

		await Promise.all([period]);
		this.modalUpdateSetShow();
	};

	handleDelete = async (row) => {
		const del = await PeriodService.deletePeriod(row.id);
		const periods = await PeriodService.getAllPeriods().then((res) => {
			this.setState({ periods: res.data });
		});

		await Promise.all([periods, del]);
		this.setState({ deleteOpen: false });
	};

	handleDeleteClick = (row) => {
		this.setState({ deleteOpen: !this.state.deleteOpen, row: row });
	};

	periodActiveIcon = (cell, row, rowIndex, formatExtraData) => {
		return (
			<div>
				{row.active === 1 ? (
					<div>
						<i
							className="fa fa-check-circle-o fa-lg"
							aria-hidden="true"
							style={{ color: 'green' }}
						></i>
					</div>
				) : (
					<div>
						<i
							className="fa fa-times fa-lg"
							aria-hidden="true"
							style={{ color: 'red' }}
						></i>
					</div>
				)}
			</div>
		);
	};
	updateDeleteIcon = (cell, row, rowIndex, formatExtraData) => {
		return (
			<div className="updateDelete">
				<i
					className="fa fa-pencil fa-lg"
					aria-hidden="true"
					onClick={() => this.handleModalUpdate(row)}
				></i>{' '}
				<i
					className="fa fa-trash fa-lg"
					aria-hidden="true"
					// () => this.handleDelete(row)
					onClick={() => this.handleDeleteClick(row)}
				></i>
			</div>
		);
	};
	formatDateStart = (cell, row, rowIndex, formatExtraData) => {
		return (
			<div className="updateDelete">
				<Moment format="DD.MM.YYYY">{row.startDate}</Moment>
			</div>
		);
	};
	formatDateEnd = (cell, row, rowIndex, formatExtraData) => {
		return (
			<div className="updateDelete">
				<Moment format="DD.MM.YYYY">{row.endDate}</Moment>
			</div>
		);
	};
	handleNameChangeAdd = (e) => {
		this.setState((prevState) => ({
			period: {
				...prevState.period,
				name: e.target.value,
			},
		}));
	};
	handleActiveChangeAdd = (e) => {
		this.setState((prevState) => ({
			period: {
				...prevState.period,
				active: e.target.value,
			},
		}));
	};
	handleStartChangeAdd = (e) => {
		this.setState((prevState) => ({
			period: {
				...prevState.period,
				startDate: e.target.value,
			},
		}));
	};
	handleEndChangeAdd = (e) => {
		this.setState((prevState) => ({
			period: {
				...prevState.period,
				endDate: e.target.value,
			},
		}));
	};
	handleAddModalSubmit = (event) => {
		const form = event.currentTarget;
		if (form.checkValidity() === false) {
			event.preventDefault();
			event.stopPropagation();
		} else {
			PeriodService.addPeriod(this.state.period);
		}
		this.setState({ validated: true });
	};
	handleNameChangeUpdate = (e) => {
		this.setState((prevState) => ({
			periodUpdate: {
				...prevState.periodUpdate,
				name: e.target.value,
			},
		}));
	};
	handleActiveChangeUpdate = (e) => {
		this.setState((prevState) => ({
			periodUpdate: {
				...prevState.periodUpdate,
				active: e.target.value,
			},
		}));
	};
	handleStartChangeUpdate = (e) => {
		this.setState((prevState) => ({
			periodUpdate: {
				...prevState.periodUpdate,
				startDate: e.target.value,
			},
		}));
	};
	handleEndChangeUpdate = (e) => {
		this.setState((prevState) => ({
			periodUpdate: {
				...prevState.periodUpdate,
				endDate: e.target.value,
			},
		}));
	};
	handleUpdateModalSubmit = (event) => {
		const form = event.currentTarget;
		if (form.checkValidity() === false) {
			event.preventDefault();
			event.stopPropagation();
		} else {
			PeriodService.updatePeriod(this.state.periodUpdate);
		}
		this.setState({ validatedUp: true });
	};
	render() {
		const columns = [
			{
				dataField: 'name',
				text: 'Naziv',
			},
			{
				dataField: 'startDate',
				text: 'Datum početka',
				formatter: this.formatDateStart,
				sort: true,
			},
			{
				dataField: 'endDate',
				text: 'Datum Završetka',
				formatter: this.formatDateEnd,
				sort: true,
			},
			{
				dataField: 'active',
				text: 'Aktivan',
				sort: true,
				formatter: this.periodActiveIcon,
			},
			{
				dataField: 'update',
				text: '',
				formatter: this.updateDeleteIcon,
			},
		];
		const { periods } = this.state;
		const options = {
			// pageStartIndex: 0,
			sizePerPage: 8,
			hideSizePerPage: true,
			hidePageListOnlyOnePage: true,
		};
		const { SearchBar } = Search;
		const rowEvents = {
			onClick: (e, row, rowIndex) => {
				console.log(
					'Selektovan je red: ' + rowIndex + ' i podaci: ' + JSON.stringify(row)
				);
			},
		};
		const {
			showAdd,
			showUpdate,
			periodUpdate,
			validated,
			validatedUp,
			deleteOpen,
			row,
		} = this.state;
		return (
			<div>
				<Dialog
					open={deleteOpen}
					onClose={this.handleDeleteClick}
					aria-labelledby="alert-dialog-title"
					aria-describedby="alert-dialog-description"
				>
					<DialogTitle id="alert-dialog-title">{'Brisanje roka'}</DialogTitle>
					<DialogContent>
						<DialogContentText id="alert-dialog-description">
							Da li ste sigurni da želite da obrišete {row.name}
						</DialogContentText>
					</DialogContent>
					<DialogActions>
						<Button onClick={this.handleDeleteClick} color="primary">
							Ne
						</Button>
						<Button onClick={() => this.handleDelete(row)} color="primary">
							Da
						</Button>
					</DialogActions>
				</Dialog>
				<Modal show={showUpdate} onHide={this.modalUpdateSetShow}>
					<Modal.Header closeButton>
						<Modal.Title>Izmeni rok </Modal.Title>
					</Modal.Header>
					<Modal.Body>
						<Form
							noValidate
							validated={validatedUp}
							onSubmit={this.handleUpdateModalSubmit}
						>
							<Form.Row>
								<Form.Group as={Col} md="4" controlId="validationCustom01">
									<Form.Label>Naziv</Form.Label>
									<Form.Control
										type="text"
										value={periodUpdate.name}
										required
										onChange={this.handleNameChangeUpdate}
									/>
									<Form.Control.Feedback type="invalid">
										Upišite naziv!
									</Form.Control.Feedback>
								</Form.Group>
								<Form.Group as={Col} md="5" controlId="validationCustom02">
									<Form.Label>Aktivan</Form.Label>
									<Form.Control
										as="select"
										value={periodUpdate.active}
										required
										onChange={this.handleActiveChangeUpdate}
									>
										<option value={''}>Odaberi...</option>
										<option value="1">Aktivan</option>
										<option value="0">Neaktivan</option>
									</Form.Control>
									<Form.Control.Feedback type="invalid">
										Odaberite aktivnost!
									</Form.Control.Feedback>
								</Form.Group>
							</Form.Row>
							<Form.Row>
								<Form.Group as={Col} md="4" controlId="validationCustom01">
									<Form.Label>Datum početka</Form.Label>
									<Form.Control
										type="date"
										value={periodUpdate.startDate}
										required
										onChange={this.handleStartChangeUpdate}
									/>
									<Form.Control.Feedback type="invalid">
										Odaberite datum početka!
									</Form.Control.Feedback>
								</Form.Group>
								<Form.Group as={Col} md="5" controlId="validationCustom02">
									<Form.Label>Datum završetka</Form.Label>

									<Form.Control
										type="date"
										value={periodUpdate.endDate}
										required
										onChange={this.handleEndChangeUpdate}
									/>
									<Form.Control.Feedback type="invalid">
										Odaberite datum završetka!
									</Form.Control.Feedback>
								</Form.Group>
							</Form.Row>
							<Modal.Footer>
								<Button variant="secondary" onClick={this.modalUpdateSetShow}>
									Zatvori
								</Button>
								<Button variant="primary" type="submit">
									Sačuvaj
								</Button>
							</Modal.Footer>
						</Form>
					</Modal.Body>
				</Modal>
				<Modal show={showAdd} onHide={this.handleModalAdd}>
					<Modal.Header closeButton>
						<Modal.Title>Dodaj novi rok</Modal.Title>
					</Modal.Header>
					<Modal.Body>
						<Form
							noValidate
							validated={validated}
							onSubmit={this.handleAddModalSubmit}
						>
							<Form.Row>
								<Form.Group as={Col} md="4" controlId="validationCustom01">
									<Form.Label>Naziv</Form.Label>
									<Form.Control
										type="text"
										required
										onChange={this.handleNameChangeAdd}
									/>
									<Form.Control.Feedback type="invalid">
										Upišite naziv!
									</Form.Control.Feedback>
								</Form.Group>
								<Form.Group as={Col} md="5" controlId="validationCustom02">
									<Form.Label>Aktivan</Form.Label>
									<Form.Control
										as="select"
										required
										onChange={this.handleActiveChangeAdd}
									>
										<option value={''}>Odaberi...</option>
										<option value="1">Aktivan</option>
										<option value="0">Neaktivan</option>
									</Form.Control>
									<Form.Control.Feedback type="invalid">
										Odaberite aktivnost!
									</Form.Control.Feedback>
								</Form.Group>
							</Form.Row>
							<Form.Row>
								<Form.Group as={Col} md="4" controlId="validationCustom01">
									<Form.Label>Datum početka</Form.Label>
									<Form.Control
										type="date"
										required
										onChange={this.handleStartChangeAdd}
									/>
									<Form.Control.Feedback type="invalid">
										Odaberite datum početka!
									</Form.Control.Feedback>
								</Form.Group>
								<Form.Group as={Col} md="5" controlId="validationCustom02">
									<Form.Label>Datum završetka</Form.Label>

									<Form.Control
										type="date"
										required
										onChange={this.handleEndChangeAdd}
									/>
									<Form.Control.Feedback type="invalid">
										Odaberite datum završetka!
									</Form.Control.Feedback>
								</Form.Group>
							</Form.Row>
							<Modal.Footer>
								<Button variant="secondary" onClick={this.handleModalUpdate}>
									Zatvori
								</Button>
								<Button variant="primary" type="submit">
									Sačuvaj
								</Button>
							</Modal.Footer>
						</Form>
					</Modal.Body>
				</Modal>
				<ToolkitProvider
					keyField="name"
					data={periods}
					columns={columns}
					search
					bootstrap4={true}
				>
					{(props) => (
						<div>
							<div className="table-search-add">
								<div className="addStudent">
									<div
										onClick={this.handleModalAdd}
										style={{ textDecoration: 'none', color: '#162833 ' }}
										className="fa fa-plus fa-border fa-2x"
										aria-hidden="true"
									></div>
								</div>
								{/* <h6>Pretraži studente</h6> */}
								<div>
									<SearchBar
										className="custome-search-field"
										{...props.searchProps}
										placeholder="Pretraži rokove"
									/>
								</div>
							</div>
							<hr />

							<BootstrapTable
								{...props.baseProps}
								rowEvents={rowEvents}
								hover
								keyField="id"
								data={periods}
								columns={columns}
								pagination={paginationFactory(options)}
							/>
						</div>
					)}
				</ToolkitProvider>
			</div>
		);
	}
}

export default AllPeriods;
