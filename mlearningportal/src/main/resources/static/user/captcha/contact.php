<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="form.js"></script>
<section class="map-area-main two">

			<div class="container-fluid">

				<div class="map-area">

					<div class="contact-bg"></div>

				</div>

			</div>

			<div class="container">

				<div class="row pb-100">
                            
					<div class="col-md-6">

						<div class="help-contact-area white-bg shadow ptb-100">

							<div class="section-title text-center">

								<h4>Send Us A Message</h4>

							</div>
                            <div id="success_message" style="width:100%; height:100%; display:none; "> <h3>Sent your message successfully!</h3> </div>
                            <div id="error_message" style="width:100%; height:100%; display:none; "> <h3>Error</h3> Sorry there was an error sending your form. </div>
							<form class="form-group"  method="post" action="send_form_email.php" role="form" id="reused_form">

                            	<div class="col-md-6">

                                    <div class="form-single">

                                        <input type="text" name="first_name" required placeholder="First name *" class="form-control">

                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <div class="form-single">

                                        <input type="text" name="last_name" required  placeholder="Last name *" class="form-control">

                                    </div>

                                </div>

                                <div class="col-md-6">

                                	<div class="form-single">

                                        <input type="text" name="email" required  placeholder="E-mail *" class="form-control">

                                    </div>

                                </div>

                                <div class="col-md-6">

                                	<div class="form-single">

                                        <input type="text" name="telephone" required  placeholder="Enter phone number *" class="form-control">

                                    </div>

                                </div>

                                <div class="col-md-12">

                                    <div class="form-textarea">

                                        <textarea name="comments" required placeholder="Massage *" rows="6" class="form-control"></textarea>

                                    </div>

                                </div>
                                  <div class="row" style="margin-bottom:30px;">
                            <div class="col-sm-5">
                                <img src="captcha.php" id="captcha_image"/>
                                <br/>
                                <a id="captcha_reload" href="#">reload</a> 
                            </div>
                            <div class="col-sm-6">
                                <label for="email">Enter the code from the image here:</label>
                                <input type="text" class="form-control" required id="captcha" name="captcha" >
                            </div>
                        </div>

								<div class="contact-button">

									<button class="contact-submit" type="submit" id="btnContactUs">Submit</button>

									<p class="form-messege">	</p>
									     
								

								</div>
								

							</form>
							
							

						</div>

					</div>

					<div class="col-md-6">

						<div class="cntct-right-adrs pl-80 pt-90">

							<div class="section-title text-left">

								<h4>Contact Us</h4>

							</div>

							<div class="adrs-details mt-60">

								<ul>

									<li>

										<a href="#"><i class="zmdi zmdi-pin"></i></a>

										<p>Shriji Complex, Maruti Mandir Road, Gadital Hadapsar, Pune 411028</p>

									</li>

									<li>

										<a href="#"><i class="zmdi zmdi-email"></i></a>

										<p class="margin-t-10">+91 626 0651370</p>

									</li>

									<li>

										<a href="#"><i class="zmdi zmdi-phone"></i></a>

										<p class="margin-t-10">connect@cinfysystems.com</p>

									</li>

								</ul>

							</div>

						</div>

					</div>

				</div>

			</div>

		</section>