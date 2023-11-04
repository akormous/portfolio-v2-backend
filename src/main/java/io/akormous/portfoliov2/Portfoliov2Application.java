package io.akormous.portfoliov2;

import io.akormous.portfoliov2.model.*;
import io.akormous.portfoliov2.service.DiscordService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;


@SpringBootApplication
public class Portfoliov2Application {
	@Value("${discord_webhook}")
	private String webhookUrl;


	public static void main(String[] args) {
		SpringApplication.run(Portfoliov2Application.class, args);
	}

	@Bean
	public Function<Message, Response> sendMail() {
		return message -> {
			try {
				DiscordService discordService = DiscordService.getInstance();
				discordService.setWebhookUrl(webhookUrl);
				if (!discordService.send(message)) {
					return new Response(new ErrorResponseBody("Oops! Your message wasn't delivered, please try to contact via email at iamakshatchauhan@gmail.com"), 500);
				}
			}
			catch (Exception e) {
				return new Response(new ErrorResponseBody("Oops! Your message wasn't delivered, please try to contact via email at iamakshatchauhan@gmail.com"), 500);
			}
			return new Response(new SuccessResponseBody("Message sent!"), 200);
		};
	}

}
