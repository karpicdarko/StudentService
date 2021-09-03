import React, { Component } from 'react';
import './allStudents.css';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import ToolkitProvider, { Search } from 'react-bootstrap-table2-toolkit';
import StudentService from '../../services/StudentService';
import Moment from 'react-moment';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

class allStudents extends Component {
	state = {
		students: [],
		deleteOpen: false,
		row: {},
	};

	async componentDidMount() {
		const students = await StudentService.getAllStudents().then((res) => {
			this.setState({ students: res.data });
		});
		await Promise.all([students]);
	}
	handleStudentSelect = (student) => {
		console.log('Kliknuo si studenta: ' + student);
	};
	handleStudentDelete = async (row) => {
		const del = await StudentService.deleteStudent(row.id);
		const students = await StudentService.getAllStudents().then((res) => {
			this.setState({ students: res.data });
		});
		await Promise.all([students, del]);
		this.setState({ deleteOpen: false });
	};
	handleDeleteClick = (row) => {
		this.setState({ deleteOpen: !this.state.deleteOpen, row: row });
	};
	render() {
		const columns = [
			{
				dataField: 'name',
				text: 'Ime',
				sort: true,
			},
			{
				dataField: 'surname',
				text: 'Smer',
				sort: true,
			},
			{
				dataField: 'indexNumber',
				text: 'Indeks',
				sort: true,
			},
		];
		const { students, deleteOpen, row } = this.state;
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
		const expandRow = {
			renderer: (row) => (
				<div className="expand-div">
					<div className="expand-data">
						<p>Ime: </p>
						<strong>{row.name}</strong>
					</div>
					<div className="expand-data">
						<p>Prezime: </p>
						<strong>{row.surname}</strong>
					</div>
					<div className="expand-data">
						<p>Ime roditelja: </p>
						<strong>{row.parents_name}</strong>
					</div>
					<div className="expand-data">
						<p>Indeks: </p>
						<strong>{row.indexNumber}</strong>
					</div>
					<div className="expand-data">
						<p>Pol: </p>
						<strong>{row.gender}</strong>
					</div>
					<div className="expand-data">
						<p>Datum rođenja: </p>
						<strong>
							<Moment format="DD.MM.YYYY">{row.birth_date}</Moment>
						</strong>
					</div>
					<div className="expand-data">
						<p>Email: </p>
						<strong>{row.email}</strong>
					</div>
					<div className="expand-data">
						<p>Adresa: </p>
						<strong>{row.address}</strong>
					</div>
					<div className="expand-data">
						<p>Grad: </p>
						<strong>{row.city}</strong>
					</div>
					<div className="expand-data">
						<p>Mobilni: </p>
						<strong>{row.cellphone}</strong>
					</div>
					<div className="expand-data">
						<p>Fiksni: </p>
						<strong>{row.landline}</strong>
					</div>
					<div className="expand-data">
						<p>Srednja škola: </p>
						<strong>{row.high_school}</strong>
					</div>
					<div className="expand-buttons">
						<Link to={'/updateStudent/' + row.id}>
							<button className="btn btn-secondary">Izmeni</button>
						</Link>
						<button
							className="btn btn-danger"
							onClick={() => this.handleDeleteClick(row)}
						>
							Obriši
						</button>
					</div>
				</div>
			),
		};
		return (
			<div>
				<Dialog
					open={deleteOpen}
					onClose={this.handleDeleteClick}
					aria-labelledby="alert-dialog-title"
					aria-describedby="alert-dialog-description"
				>
					<DialogTitle id="alert-dialog-title">
						{'Brisanje studenta'}
					</DialogTitle>
					<DialogContent>
						<DialogContentText id="alert-dialog-description">
							Da li ste sigurni da želite da obrišete studenta: {row.name}{' '}
							{row.surname}
						</DialogContentText>
					</DialogContent>
					<DialogActions>
						<Button onClick={this.handleDeleteClick} color="primary">
							Ne
						</Button>
						<Button
							onClick={() => this.handleStudentDelete(row)}
							color="primary"
						>
							Da
						</Button>
					</DialogActions>
				</Dialog>
				<ToolkitProvider
					keyField="name"
					data={students}
					columns={columns}
					search
					bootstrap4={true}
				>
					{(props) => (
						<div>
							<div className="table-search-add">
								<div className="addStudent">
									<Link
										to="/studentForm"
										style={{ textDecoration: 'none', color: '#162833 ' }}
										className="fa fa-plus fa-border fa-2x"
										aria-hidden="true"
									></Link>
								</div>
								{/* <h6>Pretraži studente</h6> */}
								<div>
									<SearchBar
										className="custome-search-field"
										{...props.searchProps}
										placeholder="Pretraži studente"
									/>
								</div>
							</div>
							<hr />

							<BootstrapTable
								{...props.baseProps}
								rowEvents={rowEvents}
								hover
								keyField="id"
								data={students}
								columns={columns}
								pagination={paginationFactory(options)}
								expandRow={expandRow}
							/>
						</div>
					)}
				</ToolkitProvider>
			</div>
		);
	}
}

export default allStudents;
