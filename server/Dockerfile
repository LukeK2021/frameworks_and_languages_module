FROM python:3.8-alpine
WORKDIR /app
COPY ./requirements.txt /app/requirements.txt
WORKDIR /app
RUN pip3 install -r requirements.txt
COPY . /app
EXPOSE 8000
ENTRYPOINT [ "python" ]
CMD [ "server.py" ]