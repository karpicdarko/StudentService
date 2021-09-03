import React, { Component } from 'react';
import './Sidebar.css';
import { Link, Redirect } from 'react-router-dom';
import RegistrarService from '../../services/RegistrarService';
import AuthService from '../../services/AuthService';

class Sidebar extends Component {
	state = {
		registrar: {},
	};

	componentDidMount() {
		RegistrarService.getRegistrarData().then((res) => {
			this.setState({ registrar: res.data });
		});
	}

	logout() {
		AuthService.logout();
	}

	render() {
		const { registrar } = this.state;

		return (
			<div className="page-wrapper chiller-theme toggled">
				{/* <a id="show-sidebar" className="btn btn-sm btn-dark" href="#">
					<i className="fas fa-bars" />
				</a> */}
				<nav id="sidebar" className="sidebar-wrapper">
					<div className="sidebar-content">
						<div className="sidebar-header">
							<div className="user-pic">
								<img
									className="img-responsive img-rounded"
									src="https://raw.githubusercontent.com/azouaoui-med/pro-sidebar-template/gh-pages/src/img/user.jpg"
									alt="User"
								/>
							</div>
							<div className="user-info">
								<Link to="/main">
									<span className="user-name">
										<strong>
											{registrar.name} {registrar.surname}
										</strong>
									</span>
								</Link>
								<span className="user-role">{registrar.email}</span>
							</div>
							<div className="close">
								{/* <span className="closeBar">
									<div id="close-sidebar">
										<i className="fa fa-times" />
									</div>
								</span> */}
							</div>
						</div>
						{/* sidebar-header  */}

						<div className="sidebar-menu">
							<ul>
								<li className="header-menu">
									<span>MENI</span>
								</li>
								<li className="sidebar-dropdown">
									<Link to="/allStudents">
										<span>Studenti</span>
									</Link>
								</li>
								<li className="sidebar-dropdown">
									<Link to="/periods">
										<span>Rokovi</span>
									</Link>
								</li>
								{/* <li className="sidebar-dropdown">
									<Link to="/">
										<span>Položeni predmeti</span>
									</Link>
								</li>
								<li className="sidebar-dropdown">
									<Link to="/">
										<span>Nepoloženi predmeti</span>
									</Link>
								</li>
								<li className="sidebar-dropdown">
									<Link to="/">
										<span>Istorija polaganja</span>
									</Link>
								</li>
								<li className="header-menu">
									<span>ISPITI</span>
								</li>
								<li>
									<Link to="/courseReg">
										<span>Prijava ispita</span>
									</Link>
								</li>
								<li>
									<Link to="/">
										<span>Odjava ispita</span>
									</Link>
								</li>
								<li>
									<Link to="/">
										<span>Prijavljeni ispiti</span>
									</Link>
								</li> */}
								<li className="header-menu">
									<Link onClick={this.logout} to="/login/registrar">
										<i className="fa fa-power-off"></i>
										<p>ODJAVA</p>
									</Link>
								</li>
							</ul>
						</div>
						{/* sidebar-menu  */}
					</div>
					{/* sidebar-content  */}
				</nav>
			</div>
		);

		// return (
		// 	<div
		// 		className={this.props.sidebarOpen ? 'sidebar_responsive' : ''}
		// 		id="sidebar"
		// 	>
		// 		<div className="sidebar__title">
		// 			<div className="sidebar__img">
		// 				<img height="50" src={logo} alt="logo" />
		// 				<h1>
		// 					Darko Karpic
		// 					<br></br> <h6>IT23/2017</h6>
		// 				</h1>
		// 			</div>
		// 			<i
		// 				onClick={() => this.props.closeSidebar()}
		// 				className="fa fa-times"
		// 				id="sidebarIcon"
		// 				aria-hidden="true"
		// 			></i>
		// 		</div>

		// 		<div className="sidebar__menu">
		// 			<div className="sidebar__link active_menu_link">
		// 				<i className="fa fa-home"></i>
		// 				<a href="/#">Dashboard</a>
		// 			</div>
		// 			<h2>INDEKS</h2>
		// 			<div className="sidebar__link">
		// 				<a href="/#">Osnovni podaci</a>
		// 			</div>
		// 			<div className="sidebar__link">
		// 				<a href="/#">Tok studija</a>
		// 			</div>
		// 			<div className="sidebar__link">
		// 				<a href="/#">Položeni predmeti</a>
		// 			</div>
		// 			<div className="sidebar__link">
		// 				<a href="/#">Nepoloženi predmeti</a>
		// 			</div>
		// 			<div className="sidebar__link">
		// 				<a href="/#">Istorija polaganja</a>
		// 			</div>
		// 			<h2>ISPITI</h2>
		// 			<div className="sidebar__link">
		// 				<a href="/#">Prijava ispita</a>
		// 			</div>
		// 			<div className="sidebar__link">
		// 				<a href="/#">Odjava ispita</a>
		// 			</div>
		// 			<div className="sidebar__link">
		// 				<a href="/#">Prijavljeni ispiti</a>
		// 			</div>

		// 			<div className="sidebar__settings">
		// 				<i class="fa fa-cog" aria-hidden="true"></i>
		// 				<a href="/#">Podešavanja</a>
		// 			</div>

		// 			<div className="sidebar__logout">
		// 				<i class="fa fa-sign-out" aria-hidden="true"></i>
		// 				<a href="/#">Odjava</a>
		// 			</div>
		// 		</div>
		// 	</div>
		// );
	}
}

export default Sidebar;
