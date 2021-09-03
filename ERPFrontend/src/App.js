import React, { Component } from 'react';
import { Switch, Route, Link, Router } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import './App.css';

import Sidebar from './components/sidebar/Sidebar';
import Dashboard from './components/dashboard/Dashboard';
import StudentData from './components/studentData/StudentData';
import Profile from './components/Profile';
import ProtectedRoute from './components/ProtectedRoute';
import ProtectedRouteS from './components/ProtectedRouteS';

import AuthService from './services/AuthService';

import Login from './components/login/Login';
import Registration from './components/registration/Registration';
import RegistrarLogin from './components/login/RegistrarLogin';
import SidebarRegistrar from './components/sidebar/SidebarRegistrar';
import allStudents from './components/allStudents/allStudents';
import AddStudent from './components/studentForm/AddStudent';
import UpdateStudent from './components/studentForm/UpdateStudent';
import Unregister from './components/unregister/Unregister';
import AllPeriods from './components/allPeriods/AllPeriods';
import courseOfStudy from './components/courseOfStudy/courseOfStudy';

class App extends Component {
	constructor(props) {
		super(props);
		this.logOut = this.logOut.bind(this);

		this.state = {
			currentUser: undefined,
		};
	}

	componentDidMount() {
		const user = AuthService.getCurrentUser();

		if (user) {
			this.setState({
				currentUser: user,
			});
		}
	}

	logOut() {
		AuthService.logout();
	}

	render() {
		const { currentUser } = this.state;
		if (currentUser !== undefined) {
			if (currentUser.roles[0] === 'ROLE_REGISTRAR') {
				return (
					<div className="root">
						<Route exact path="/login/registrar" component={RegistrarLogin} />

						<div className="root2">
							<div className="sidebar">
								<ProtectedRoute component={SidebarRegistrar} />
							</div>
							<div className="content">
								<Switch>
									<Route
										exact
										path={['/', '/main', '/allStudents']}
										component={allStudents}
									/>
									<Route exact path="/studentForm" component={AddStudent} />
									<Route
										exact
										path="/updateStudent/:id"
										component={UpdateStudent}
									/>
									<Route exact path="/periods" component={AllPeriods} />
								</Switch>
							</div>
						</div>
					</div>
				);
			}
			return (
				<div className="root">
					<Route exact path="/login/student" component={Login} />
					<div className="root2">
						<div className="sidebar">
							<ProtectedRouteS component={Sidebar} />
						</div>
						<div className="content">
							<Switch>
								<Route exact path={['/', '/main']} component={Dashboard} />
								<Route exact path="/data" component={StudentData} />
								<Route exact path="/courseReg" component={Registration} />
								<Route exact path="/courseUnreg" component={Unregister} />
								<Route exact path="/courseOS" component={courseOfStudy} />
							</Switch>
						</div>
					</div>
				</div>
			);
		}
		return (
			<div>
				<Route
					exact
					path={['/', '/login/registrar']}
					component={RegistrarLogin}
				/>
				<Route exact path="/login/student" component={Login} />
			</div>
		);
	}
}

export default App;
