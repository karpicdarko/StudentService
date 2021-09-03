import React, { Component } from 'react';
import '../login/Login.css';
import Form from 'react-validation/build/form';
import Input from 'react-validation/build/input';
import CheckButton from 'react-validation/build/button';
import AuthService from '../../services/AuthService';
import { Icon } from '@iconify/react';
import { withRouter } from 'react-router-dom';
import iosLockedOutline from '@iconify-icons/ion/ios-locked-outline';

const required = (value) => {
	if (!value) {
		return (
			<div className="alert alert-danger" role="alert">
				This field is required!
			</div>
		);
	}
};

class Login extends Component {
	constructor(props) {
		super(props);
		this.handleLogin = this.handleLogin.bind(this);
		this.onChangeUsername = this.onChangeUsername.bind(this);
		this.onChangePassword = this.onChangePassword.bind(this);

		this.state = {
			username: '',
			password: '',
			loading: false,
			message: '',
		};
	}

	onChangeUsername(e) {
		this.setState({
			username: e.target.value,
		});
	}

	onChangePassword(e) {
		this.setState({
			password: e.target.value,
		});
	}

	handleLogin(e) {
		e.preventDefault();

		this.setState({
			message: '',
			loading: true,
		});

		this.form.validateAll();

		if (this.checkBtn.context._errors.length === 0) {
			AuthService.login(this.state.username, this.state.password).then(
				() => {
					console.log(this.props.history);
					this.props.history.push('/main');
					window.location.reload();
				},
				(error) => {
					const resMessage =
						(error.response &&
							error.response.data &&
							error.response.data.message) ||
						error.message ||
						error.toString();

					this.setState({
						loading: false,
						message: resMessage,
					});
				}
			);
		} else {
			this.setState({
				loading: false,
			});
		}
	}

	render() {
		return (
			<div className="login-dark">
				<Form
					onSubmit={this.handleLogin}
					ref={(c) => {
						this.form = c;
					}}
				>
					<h2 className="sr-only">Login Form</h2>
					<div className="illustration">
						<Icon icon={iosLockedOutline} />
					</div>
					<div className="form-group">
						<Input
							className="form-control"
							type="text"
							name="username"
							placeholder="Index"
							value={this.state.username}
							onChange={this.onChangeUsername}
							validations={[required]}
						/>
					</div>
					<div className="form-group">
						<Input
							className="form-control"
							type="password"
							name="password"
							placeholder="Password"
							value={this.state.password}
							onChange={this.onChangePassword}
							validations={[required]}
						/>
					</div>
					<div className="form-group">
						<button
							className="btn btn-primary btn-block"
							disabled={this.state.loading}
						>
							{this.state.loading && (
								<span className="spinner-border spinner-border-sm"></span>
							)}
							<span>Login</span>
						</button>
					</div>
					{this.state.message && (
						<div className="form-group">
							<div className="alert alert-danger" role="alert">
								{this.state.message}
							</div>
						</div>
					)}
					<CheckButton
						style={{ display: 'none' }}
						ref={(c) => {
							this.checkBtn = c;
						}}
					/>
				</Form>
			</div>
		);
	}
}

export default withRouter(Login);
