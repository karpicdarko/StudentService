import React, { Component } from 'react';
import SyllabusService from '../../services/SyllabusService';
import CorStuService from '../../services/CorStuService';
import { Card, Container, Row, Col } from 'react-bootstrap';

class courseOfStudy extends Component {
	state = {
		syllabus: {},
		courseOS: [],
	};
	componentDidMount() {
		SyllabusService.getSyllabusData().then((res) => {
			this.setState({ syllabus: res.data });
		});
		CorStuService.getAllCorStu().then((res) => {
			this.setState({ courseOS: res.data });
		});
	}

	render() {
		const { syllabus, courseOS } = this.state;
		return (
			<div>
				<Container>
					<Row>
						{courseOS.map((course) => (
							<Col xs="4">
								{/* <div>{syllabus.name}</div>
						<div>{course.year}</div>
						<div>{course.financing_method}</div> */}
								<Card style={{ width: '18rem', margin: '10px' }} bg={'light'}>
									<Card.Body>
										<Card.Subtitle className="mb-2 text-muted">
											Studijski program
										</Card.Subtitle>
										<Card.Title>{syllabus.name}</Card.Title>
										<hr />
										<Card.Subtitle className="mb-2 text-muted">
											Godina
										</Card.Subtitle>
										<Card.Title>{course.year}</Card.Title>
										<hr />
										<Card.Subtitle className="mb-2 text-muted">
											Način finansiranja
										</Card.Subtitle>
										<Card.Title>{course.financing_method}</Card.Title>
									</Card.Body>
								</Card>
							</Col>
						))}
					</Row>
				</Container>
			</div>
		);
	}
}

export default courseOfStudy;
