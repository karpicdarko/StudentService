import React from 'react';
import { Route, Redirect } from 'react-router-dom';

const ProtectedRoute = ({ component: Component, ...rest }) => {
	return (
		<Route
			{...rest}
			render={(props) =>
				localStorage.getItem('user') ? (
					<Component {...rest} {...props} />
				) : (
					<Redirect to="/login/registrar" />
				)
			}
		/>
	);
};

export default ProtectedRoute;
