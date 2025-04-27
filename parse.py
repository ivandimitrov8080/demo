import mailbox
import email

maildir = mailbox.Maildir("/home/ivand/mail/ivan/INBOX")

for message_key, message in maildir.items():
    subject = message['subject']
    sender = message['from']
    date = message['date']
    for header, value in message.items():
        print(f"{header}: {value}")
    break

    # print(f'Subject: {subject}')
    # print(f'From: {sender}')
    # print(f'Date: {date}')
    # print('-' * 40)
