import csv
import subprocess
from datetime import datetime, timedelta
from zoneinfo import ZoneInfo

from time import sleep


def main():
    in_path = '/Users/michael/Documents/export/beddit-2021-04-17-munged.csv'

    with open(in_path) as in_file:
        reader = csv.DictReader(in_file)

        for row in reader:
            beddit_today_date = datetime.strptime(row["ZDATEYMD"], '%Y%m%d')
            beddit_rating = row['ZRATING']
            beddit_text = row['ZNOTETEXT']
            short_date_format = '%B %-e'
            beddit_today_date_string = beddit_today_date.strftime(
                short_date_format)
            beddit_yesterday_date = beddit_today_date - timedelta(days=1)
            beddit_yesterday_date_string = beddit_yesterday_date.strftime(
                short_date_format)
            beddit_rating_night = f'night of {beddit_yesterday_date_string}' \
                                  f'–{beddit_today_date_string}'
            entry_date = beddit_yesterday_date + timedelta(hours=21)
            entry_latitude = 37.7598118
            entry_longitude = -122.2430435
            entry_tz_string = 'America/Los_Angeles'
            entry_date = entry_date.astimezone(ZoneInfo(entry_tz_string))
            entry_date_string = entry_date.strftime('%Y-%m-%d 21:00')
            entry_lines = []

            if beddit_text:
                entry_lines.append(beddit_text)

            if beddit_rating:
                entry_lines.append(f'Beddit sleep rating: {beddit_rating}'
                                   f' _[for {beddit_rating_night}]_')

            entry_text = '\n\n'.join(entry_lines)

            import_command = [
                'dayone2', '--date', entry_date_string,
                '--time-zone', entry_tz_string,
                '--coordinate', str(entry_latitude), str(entry_longitude),
                'new'
            ]
            subprocess.run(import_command, input=entry_text.encode())

            # Apparently the address for the location (as specified by the
            # --coordinate option) is not fetched properly sometimes if new
            # entries are created too rapidly; a ten-second sleep after each
            # entry is created is almost sufficient to avoid the problem (it
            # still happened twice during the processing of 1,152 entries).

            sleep(10)


if __name__ == '__main__':
    main()
